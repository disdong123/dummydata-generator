package kr.disdong.dummydata.generator.persistence.output

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import kr.disdong.dummydata.generator.persistence.module.post.model.PostEntity
import kr.disdong.dummydata.generator.persistence.module.user.model.UserEntity

public object GenFixture {
  private val monkey: FixtureMonkey = FixtureMonkey.builder()
          .plugin(KotlinPlugin())
          .registerGroup(ArbitraryBuilderGroupEx())
          .build()

  public fun userEntity(): UserEntity = monkey.giveMeBuilder<UserEntity>().sample()

  public fun postEntity(): PostEntity = monkey.giveMeBuilder<PostEntity>().sample()
}
