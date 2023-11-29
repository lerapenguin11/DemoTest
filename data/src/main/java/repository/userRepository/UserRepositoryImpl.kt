package repository.userRepository

import com.example.domain.common.ResultTest
import com.example.domain.entity.user.Token
import com.example.domain.entity.user.User
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteSource: UserRemoteSource
) : UserRepository{
    override suspend fun getLogin(user: User): ResultTest<Token> {
        return remoteSource.getLogin(user = user)
    }
}