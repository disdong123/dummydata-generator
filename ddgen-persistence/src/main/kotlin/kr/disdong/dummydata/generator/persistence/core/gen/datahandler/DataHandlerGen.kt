package kr.disdong.dummydata.generator.persistence.core.gen.datahandler

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import jakarta.persistence.Entity
import kr.disdong.dummydata.generator.persistence.core.gen.GenOptions
import org.reflections.Reflections
import org.springframework.context.ApplicationContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.File
import kotlin.reflect.KClass

object DataHandlerGen {

    fun `do`(options: GenOptions) {
        val repositoryClass = ClassName(JpaRepository::class.java.packageName, JpaRepository::class.java.simpleName)
        val kClass = ClassName(KClass::class.java.packageName, KClass::class.java.simpleName)
        val entityAnnotation = ClassName(Entity::class.java.packageName, Entity::class.java.simpleName)
        val reflectionsClass = ClassName(Reflections::class.java.packageName, Reflections::class.java.simpleName)
        val anyClass = Any::class.asClassName()
        val contextClass = ClassName(ApplicationContext::class.java.packageName, ApplicationContext::class.java.simpleName)

        val mapOfKClassToRepo = Map::class.asClassName().parameterizedBy(
            kClass.parameterizedBy(anyClass),
            repositoryClass.parameterizedBy(anyClass, anyClass)
        )

        val initRepositoryMapFunc = FunSpec.builder("initRepositoryMap")
            .addModifiers(KModifier.PRIVATE)
            .returns(mapOfKClassToRepo)
            .addCode(
                """
                val entityPackage = "${options.entityPackageName}"
    
                val reflections = %T(entityPackage)
                val entityClasses = reflections.getTypesAnnotatedWith(%T::class.java)
                    .mapNotNull { it.kotlin as? %T<Any> }
    
                val repositoryMap = mutableMapOf<%T<Any>, %T<Any, Any>>()
                for (entityClass in entityClasses) {
                    val repositoryBeanName = entityClass.simpleName!!.replace("Entity", "").plus("Repository").decapitalize()
                    if (repositoryBeanName != null && context.containsBean(repositoryBeanName)) {
                        val repository = context.getBean(repositoryBeanName) as? %T<Any, Any>
                        if (repository != null) {
                            repositoryMap[entityClass] = repository
                        }
                    }
                }
                return repositoryMap
                """.trimIndent(),
                reflectionsClass, entityAnnotation, kClass, kClass, repositoryClass, repositoryClass
            )
            .build()

        val doFunc = FunSpec.builder("do")
            .addModifiers(KModifier.PUBLIC)
            .addParameter("count", Int::class)
            .addCode(
                """
                repositoryMap.forEach { (entityClass, repository) ->
                    val list = mutableListOf<Any>()
                    for (i in 1..count) {
                        val entity = GenFixture::class.members.find { it.name == entityClass.simpleName?.decapitalize() }?.call(GenFixture) as Any
                        list.add(entity)
                    }
                    repository.saveAll(list)
                }
                """.trimIndent()
            )
            .addAnnotation(Transactional::class)
            .build()

        val dataHandlerClass = TypeSpec.classBuilder(options.dataHandlerFileName)
            .addAnnotation(Component::class)
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter("context", contextClass)
                    .build()
            )
            .addProperty(
                PropertySpec.builder("context", contextClass, KModifier.PRIVATE)
                    .initializer("context")
                    .build()
            )
            .addProperty(
                PropertySpec.builder("repositoryMap", mapOfKClassToRepo, KModifier.PRIVATE)
                    .initializer("initRepositoryMap()")
                    .build()
            )
            .addFunction(doFunc)
            .addFunction(initRepositoryMapFunc)
            .build()

        val file = FileSpec.builder(options.outputPackageName, options.dataHandlerFileName)
            .addType(dataHandlerClass)
            .build()

        file.writeTo(File(options.outputDir))
    }
}
