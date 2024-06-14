package kr.disdong.dummydata.generator.persistence.output

import com.navercorp.fixturemonkey.buildergroup.ArbitraryBuilderGroup
import com.navercorp.fixturemonkey.resolver.ArbitraryBuilderCandidateFactory
import com.navercorp.fixturemonkey.resolver.ArbitraryBuilderCandidateList
import kr.disdong.dummydata.generator.persistence.module.post.model.PostEntity
import kr.disdong.dummydata.generator.persistence.module.user.model.UserEntity
import net.jqwik.api.Arbitraries

public class ArbitraryBuilderGroupEx : ArbitraryBuilderGroup {
  override fun generateCandidateList(): ArbitraryBuilderCandidateList {
    val builderList = ArbitraryBuilderCandidateList.create()
    builderList.add(
    ArbitraryBuilderCandidateFactory.of(UserEntity::class.java)
    .builder {
        it.set("name", Arbitraries.strings().ofMinLength(0).ofMaxLength(100))
        it.set("phone", Arbitraries.strings().ofMinLength(0).ofMaxLength(20))
    }
    );
    builderList.add(
    ArbitraryBuilderCandidateFactory.of(PostEntity::class.java)
    .builder {
        it.set("name", Arbitraries.strings().ofMinLength(0).ofMaxLength(100))
        it.set("phone", Arbitraries.strings().ofMinLength(0).ofMaxLength(20))
    }
    );
    return builderList
  }
}
