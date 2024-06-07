package kr.disdong.dummydata.generator.server.core.util

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import kr.disdong.dummydata.generator.server.ArbitraryBuilderGroupEx

object FixtureUtil {
    fun monkey(): FixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .registerGroup(ArbitraryBuilderGroupEx())
        .build()
}
