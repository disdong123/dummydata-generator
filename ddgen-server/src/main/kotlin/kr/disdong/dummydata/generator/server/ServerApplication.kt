package kr.disdong.dummydata.generator.server

import kr.disdong.dummydata.generator.persistence.PersistenceApplication
import kr.disdong.dummydata.generator.persistence.core.gen.GenOptions
import kr.disdong.dummydata.generator.persistence.core.gen.arbitrarybuildergroup.ArbitraryBuilderGroupGen
import kr.disdong.dummydata.generator.persistence.core.gen.datahandler.DataHandlerGen
import kr.disdong.dummydata.generator.persistence.core.gen.fixture.FixtureGen
import kr.disdong.dummydata.generator.persistence.core.gen.repository.RepositoryGen
import kr.disdong.dummydata.generator.persistence.core.util.EntityScanner
import kr.disdong.dummydata.generator.persistence.output.DataHandler
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component

@Component
class Generator(
    private val dataHandler: DataHandler,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        // val options = GenOptions()
        // val entities = EntityScanner.scan(options.entityPackageName)
        //
        // ArbitraryBuilderGroupGen.`do`(entities, options)
        // FixtureGen.`do`(entities, options)
        // RepositoryGen.`do`(entities, options)
        // DataHandlerGen.`do`(options)

        dataHandler.`do`(100)
    }
}

@SpringBootApplication
@Import(PersistenceApplication::class)
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
