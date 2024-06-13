package kr.disdong.dummydata.generator.server.core.gen

class GenOptions(
    val scanPackageName: String = "kr.disdong.dummydata.generator.persistence",
    val outputDir: String = "ddgen-server/src/main/kotlin",
    val outputPackageName: String = "kr.disdong.dummydata.generator.server.output",
    val constraintClassName: String = "ArbitraryBuilderGroupEx",
    val fixtureClassName: String = "GenFixture",
    val repositoryFileName: String = "GenRepository",
    val count: Long = 20L,
)
