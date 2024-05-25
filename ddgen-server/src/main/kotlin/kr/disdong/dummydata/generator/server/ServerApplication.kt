package kr.disdong.dummydata.generator.server

import kr.disdong.dummydata.generator.persistence.PersistenceApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(PersistenceApplication::class)
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
