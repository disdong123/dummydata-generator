package kr.disdong.dummydata.generator.persistence.core.gen

class GenOptions(
    val entityPackageName: String = "kr.disdong.dummydata.generator.persistence",
    val outputDir: String = "ddgen-persistence/src/main/kotlin",
    val outputPackageName: String = "kr.disdong.dummydata.generator.persistence.output",
    val constraintClassName: String = "ArbitraryBuilderGroupEx",
    val fixtureClassName: String = "GenFixture",
    val repositoryFileName: String = "GenRepository",
    val dataHandlerFileName: String = "DataHandler",
)
