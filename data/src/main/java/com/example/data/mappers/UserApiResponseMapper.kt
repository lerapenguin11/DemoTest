package com.example.data.mappers

import com.example.data.api.model.TokenResponse
import com.example.data.api.model.UserRequest
import com.example.domain.entity.Response
import com.example.domain.entity.Token
import com.example.domain.entity.User

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