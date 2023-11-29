package repository.userRepository

import com.example.domain.common.ResultTest
import com.example.domain.entity.user.Token
import com.example.domain.entity.user.User

interface UserRemoteSource {

    suspend fun getLogin(user: User) : ResultTest<Token>
}