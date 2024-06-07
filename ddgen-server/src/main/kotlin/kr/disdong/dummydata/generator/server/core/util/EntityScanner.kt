package kr.disdong.dummydata.generator.server.core.util

import jakarta.persistence.Entity
import org.reflections.Reflections

object EntityScanner {
    fun scan(packageName: String): MutableSet<Class<*>> {
        return Reflections(packageName).getTypesAnnotatedWith(Entity::class.java)
    }
}
