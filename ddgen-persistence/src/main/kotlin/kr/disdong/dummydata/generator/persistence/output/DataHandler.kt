package kr.disdong.dummydata.generator.persistence.output

import jakarta.persistence.Entity
import kotlin.Any
import kotlin.Int
import kotlin.collections.Map
import kotlin.reflect.KClass
import org.reflections.Reflections
import org.springframework.`data`.jpa.repository.JpaRepository
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.springframework.transaction.`annotation`.Transactional

@Component
public class DataHandler(
  private val context: ApplicationContext,
) {
  private val repositoryMap: Map<KClass<Any>, JpaRepository<Any, Any>> = initRepositoryMap()

  @Transactional
  public fun `do`(count: Int) {
    repositoryMap.forEach { (entityClass, repository) ->
        val list = mutableListOf<Any>()
        for (i in 1..count) {
            val entity = GenFixture::class.members.find { it.name ==
        entityClass.simpleName?.decapitalize() }?.call(GenFixture) as Any
            list.add(entity)
        }
        repository.saveAll(list)
    }
  }

  private fun initRepositoryMap(): Map<KClass<Any>, JpaRepository<Any, Any>> {
    val entityPackage = "kr.disdong.dummydata.generator.persistence"

    val reflections = Reflections(entityPackage)
    val entityClasses = reflections.getTypesAnnotatedWith(Entity::class.java)
        .mapNotNull { it.kotlin as? KClass<Any> }

    val repositoryMap = mutableMapOf<KClass<Any>, JpaRepository<Any, Any>>()
    for (entityClass in entityClasses) {
        val repositoryBeanName = entityClass.simpleName!!.replace("Entity",
        "").plus("Repository").decapitalize()
        if (repositoryBeanName != null && context.containsBean(repositoryBeanName)) {
            val repository = context.getBean(repositoryBeanName) as? JpaRepository<Any, Any>
            if (repository != null) {
                repositoryMap[entityClass] = repository
            }
        }
    }
    return repositoryMap
  }
}
