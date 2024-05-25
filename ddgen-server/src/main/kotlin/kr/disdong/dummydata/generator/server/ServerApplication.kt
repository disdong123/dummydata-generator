package kr.disdong.dummydata.generator.server

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.buildergroup.ArbitraryBuilderGroup
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import com.navercorp.fixturemonkey.resolver.ArbitraryBuilderCandidateFactory
import com.navercorp.fixturemonkey.resolver.ArbitraryBuilderCandidateList
import kr.disdong.dummydata.generator.persistence.PersistenceApplication
import kr.disdong.dummydata.generator.persistence.module.user.model.UserEntity
import kr.disdong.dummydata.generator.persistence.module.user.repository.UserRepository
import net.jqwik.api.Arbitraries
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

object FixtureUtil {
    fun monkey(): FixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .registerGroup(ArbitraryBuilderGroupEx())
        .build()
}

object UserEntityFixture {
    fun any(): UserEntity = FixtureUtil.monkey()
        .giveMeBuilder<UserEntity>()
        // .setExp(UserEntity::name, Arbitraries.strings().ofMinLength(3).ofMaxLength(3))
        .setExp(UserEntity::phone, Arbitraries.strings().ofMinLength(19).ofMaxLength(20))
        .sample()
}

/**
 * TODO
 *  얘를 활용해볼까?
 *  1. @Entity 가 있는 클래스 목록 가져오기
 *  2. 컬럼 제약조건(@Column 의 length 등) 확인해서 ArbitraryBuilderGroup 를 구현한 코드 생성
 *  3. 프로젝트 빌드 시 FixtureUtil 자동 생성
 *  또는 @DummyGen 어노테이션 같은걸 만들어서 얘가 붙은 애들로.
 */
class ArbitraryBuilderGroupEx : ArbitraryBuilderGroup {
    override fun generateCandidateList(): ArbitraryBuilderCandidateList {
        return ArbitraryBuilderCandidateList.create()
            .add(
                ArbitraryBuilderCandidateFactory.of(UserEntity::class.java)
                    .builder { it.set("name", Arbitraries.strings().ofMinLength(3).ofMaxLength(3)) }
            )
    }
}
