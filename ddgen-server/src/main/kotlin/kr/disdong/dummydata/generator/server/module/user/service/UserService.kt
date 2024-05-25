package kr.disdong.dummydata.generator.server.module.user.service

import kr.disdong.dummydata.generator.persistence.module.user.repository.UserRepository
import kr.disdong.dummydata.generator.server.module.user.dto.CreateUserBody
import kr.disdong.dummydata.generator.server.module.user.dto.CreateUserResponse
import kr.disdong.dummydata.generator.server.module.user.exception.UserNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun getByUserId(userId: Long) =
        CreateUserResponse.of(
            userRepository.findByUserId(userId)
                ?: throw UserNotFound(userId)
        )

    @Transactional
    fun create(request: CreateUserBody): CreateUserResponse {
        return CreateUserResponse.of(userRepository.save(request.toUserEntity()))
    }
}
