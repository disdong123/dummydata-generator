package kr.disdong.dummydata.generator.server.module.user.controller.spec

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.disdong.dummydata.generator.common.dto.TemplateResponse
import kr.disdong.dummydata.generator.server.module.user.dto.CreateUserBody
import kr.disdong.dummydata.generator.server.module.user.dto.CreateUserResponse

@Tag(name = "유저")
interface UserSpec {

    @Operation
    fun getByUserId(userId: Long): TemplateResponse<CreateUserResponse>

    @Operation
    fun create(body: CreateUserBody): TemplateResponse<CreateUserResponse>
}
