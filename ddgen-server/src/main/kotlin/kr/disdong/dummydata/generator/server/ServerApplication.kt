package kr.disdong.dummydata.generator.server

import kr.disdong.dummydata.generator.persistence.PersistenceApplication
import kr.disdong.dummydata.generator.server.core.gen.GenOptions
import kr.disdong.dummydata.generator.server.core.gen.arbitrarybuildergroup.ArbitraryBuilderGroupGen
import kr.disdong.dummydata.generator.server.core.gen.fixture.FixtureGen
import kr.disdong.dummydata.generator.server.core.gen.repository.RepositoryGen
import kr.disdong.dummydata.generator.server.core.util.EntityScanner
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
@Import(PersistenceApplication::class)
class ServerApplication : ApplicationRunner {
    @Transactional
    override fun run(args: ApplicationArguments?) {
        val options = GenOptions()
        val entities = EntityScanner.scan(options.scanPackageName)

        ArbitraryBuilderGroupGen.`do`(entities, options)
        FixtureGen.`do`(entities, options)
        RepositoryGen.`do`(entities, options)
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
