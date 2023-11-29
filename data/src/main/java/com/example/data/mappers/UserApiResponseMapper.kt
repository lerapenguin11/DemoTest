package com.example.data.mappers

import com.example.data.api.model.user.TokenResponse
import com.example.data.api.model.user.UserRequest
import com.example.domain.entity.user.Response
import com.example.domain.entity.user.Token
import com.example.domain.entity.user.User

class UserApiResponseMapper {

    fun toVolumeUserRequest(request: User): UserRequest {
        return UserRequest(
            login = request.login,
            password = request.password)
    }

    fun toVolumeToken(response: TokenResponse): Token {
        return Token(response = Response(response.response.token),
        success = response.success)
    }
}