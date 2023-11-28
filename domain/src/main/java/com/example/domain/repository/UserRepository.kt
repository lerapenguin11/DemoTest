package com.example.domain.repository

import com.example.domain.common.ResultTest
import com.example.domain.entity.Token
import com.example.domain.entity.User

interface UserRepository {

    suspend fun getLogin(user: User) : ResultTest<Token>
}