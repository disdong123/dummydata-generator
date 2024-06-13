package kr.disdong.dummydata.generator.persistence.module.user.repository

import kr.disdong.dummydata.generator.persistence.module.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long>
