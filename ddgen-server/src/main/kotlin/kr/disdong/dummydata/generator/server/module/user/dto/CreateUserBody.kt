package kr.disdong.dummydata.generator.server.module.user.dto

import kr.disdong.dummydata.generator.persistence.module.user.model.UserEntity

class CreateUserBody(
    val name: String,
    val phone: String,
) {

    fun toUserEntity(): UserEntity {
        return UserEntity(
            name = name,
            phone = phone,
        )
    }
}

class CreateUserResponse(
    val name: String,
    val phone: String,
) {
    companion object {
        fun of(userEntity: UserEntity) = CreateUserResponse(
            name = userEntity.name,
            phone = userEntity.phone,
        )
    }
}
