package kr.disdong.dummydata.generator.persistence.core.gen.fixture

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import java.util.Locale

class FunctionGen(
    private val entities: MutableSet<Class<*>>
) {

    fun `do`(): List<FunSpec> {
        return entities.map { entity -> createFunctionForEntity(entity) }
    }

    private fun createFunctionForEntity(entity: Class<*>): FunSpec {
        val entityName = entity.simpleName.replaceFirstChar { it.lowercase(Locale.getDefault()) }

        return FunSpec.builder(entityName)
            .addModifiers(KModifier.PUBLIC)
            .returns(entity)
            .addStatement("return monkey.giveMeBuilder<%T>().sample()", entity)
            .build()
    }
}
