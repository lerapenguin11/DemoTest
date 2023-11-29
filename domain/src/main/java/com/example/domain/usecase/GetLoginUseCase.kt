package com.example.domain.usecase

import com.example.domain.entity.user.User
import com.example.domain.repository.UserRepository

class GetLoginUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(user : User) = repository.getLogin(user = user)
}