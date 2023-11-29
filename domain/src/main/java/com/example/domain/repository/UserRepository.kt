package com.example.domain.repository

import com.example.domain.common.ResultTest
import com.example.domain.entity.user.Token
import com.example.domain.entity.user.User

interface UserRepository {

    suspend fun getLogin(user: User) : ResultTest<Token>
}