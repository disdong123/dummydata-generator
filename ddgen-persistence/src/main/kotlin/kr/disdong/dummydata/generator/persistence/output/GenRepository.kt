package kr.disdong.dummydata.generator.persistence.output

import kotlin.Long
import kr.disdong.dummydata.generator.persistence.module.post.model.PostEntity
import kr.disdong.dummydata.generator.persistence.module.user.model.UserEntity
import org.springframework.`data`.jpa.repository.JpaRepository

public interface UserRepository : JpaRepository<UserEntity, Long>

public interface PostRepository : JpaRepository<PostEntity, Long>
