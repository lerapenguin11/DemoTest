package repository

import com.example.domain.common.ResultTest
import com.example.domain.entity.Token
import com.example.domain.entity.User

interface UserRemoteSource {

    suspend fun getLogin(user: User) : ResultTest<Token>
}