package kr.disdong.dummydata.generator.server

import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import kr.disdong.dummydata.generator.persistence.PersistenceApplication
import kr.disdong.dummydata.generator.persistence.module.user.model.UserEntity
import kr.disdong.dummydata.generator.persistence.module.user.repository.UserRepository
import kr.disdong.dummydata.generator.server.core.gen.Gen
import kr.disdong.dummydata.generator.server.core.gen.GenOptions
import kr.disdong.dummydata.generator.server.core.util.FixtureUtil
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
@Import(PersistenceApplication::class)
class ServerApplication(
    private val userRepository: UserRepository
) : ApplicationRunner {
    @Transactional
    override fun run(args: ApplicationArguments?) {
        Gen.`do`(GenOptions())
        val u = UserEntityFixture.any()
        println(u.name)
        println(u.name.length)
        println(u.phone)
        println(u.phone.length)
        println(userRepository.save(u))
        println(userRepository.findAll())
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}

object UserEntityFixture {
    fun any(): UserEntity = FixtureUtil.monkey()
        .giveMeBuilder<UserEntity>()
        .sample()
}
