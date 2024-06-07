package kr.disdong.dummydata.generator.server.core.gen

import kr.disdong.dummydata.generator.server.core.util.EntityScanner
import java.io.File

class GenOptions(
    val scanPackageName: String = "kr.disdong.dummydata.generator.persistence",
    val outputDir: String = "ddgen-server/src/main/kotlin",
    val outputPackageName: String = "kr.disdong.dummydata.generator.server",
    val outputClassName: String = "ArbitraryBuilderGroupEx",
)

object Gen {

    fun `do`(options: GenOptions) {
        val entities = EntityScanner.scan(options.scanPackageName)

        val file = FileGen(
            packageName = options.outputPackageName,
            fileName = options.outputClassName,
            classGen = ClassGen(
                className = options.outputClassName,
                functionGen = FunctionGen(entities)
            )
        ).`do`()

        file.writeTo(File(options.outputDir))
    }
}
