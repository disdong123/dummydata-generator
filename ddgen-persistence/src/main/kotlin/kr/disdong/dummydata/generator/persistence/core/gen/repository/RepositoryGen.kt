package kr.disdong.dummydata.generator.persistence.core.gen.repository

import ClassGen
import FileGen
import kr.disdong.dummydata.generator.persistence.core.gen.GenOptions
import java.io.File

object RepositoryGen {

    fun `do`(entities: MutableSet<Class<*>>, options: GenOptions) {
        val file = FileGen(
            packageName = options.outputPackageName,
            fileName = options.repositoryFileName,
            classGens = entities.map {
                ClassGen(
                    entityPackageName = it.packageName,
                    entityName = it.simpleName,
                    className = it.simpleName.replace("Entity", "") + "Repository",
                )
            }
        ).`do`()

        file.writeTo(File(options.outputDir))
    }
}
