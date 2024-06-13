package kr.disdong.dummydata.generator.server.core.gen.fixture

import kr.disdong.dummydata.generator.server.core.gen.GenOptions
import java.io.File

object FixtureGen {

    fun `do`(entities: MutableSet<Class<*>>, options: GenOptions) {
        val file = FileGen(
            packageName = options.outputPackageName,
            fileName = options.fixtureClassName,
            classGen = ClassGen(
                outputPackageName = options.outputPackageName,
                constraintClassName = options.constraintClassName,
                className = options.fixtureClassName,
                functionGen = FunctionGen(entities)
            )
        ).`do`()

        file.writeTo(File(options.outputDir))
    }
}
