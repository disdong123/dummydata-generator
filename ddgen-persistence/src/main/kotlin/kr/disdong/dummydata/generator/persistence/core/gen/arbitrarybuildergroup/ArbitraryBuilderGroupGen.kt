package kr.disdong.dummydata.generator.persistence.core.gen.arbitrarybuildergroup

import kr.disdong.dummydata.generator.persistence.core.gen.GenOptions
import java.io.File

object ArbitraryBuilderGroupGen {

    fun `do`(entities: MutableSet<Class<*>>, options: GenOptions) {
        val file = FileGen(
            packageName = options.outputPackageName,
            fileName = options.constraintClassName,
            classGen = ClassGen(
                className = options.constraintClassName,
                functionGen = FunctionGen(entities)
            )
        ).`do`()

        file.writeTo(File(options.outputDir))
    }
}
