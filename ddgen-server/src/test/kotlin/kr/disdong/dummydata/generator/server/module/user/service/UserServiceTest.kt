package kr.disdong.dummydata.generator.server.module.user.service

import com.navercorp.fixturemonkey.buildergroup.ArbitraryBuilderGroup
import org.junit.jupiter.api.Test

internal class UserServiceTest {

    @Test
    fun a() {
        println(ArbitraryBuilderGroup::class.java.name)
        println(ArbitraryBuilderGroup::class.java.packageName)
        println(ArbitraryBuilderGroup::class.java.simpleName)
        println(ArbitraryBuilderGroup::class.java.declaredMethods[0].name)
    }
}
