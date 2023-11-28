package repository

import com.example.data.api.TestApi
import com.example.data.mappers.UserApiResponseMapper
import com.example.domain.common.ResultTest
import com.example.domain.entity.Token
import com.example.domain.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRemoteSourceImpl(
    private val service : TestApi,
    private val mapper: UserApiResponseMapper
) : UserRemoteSource{

    override suspend fun getLogin(user: User): ResultTest<Token> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getLogin(mapper.toVolumeUserRequest(user))
                if (response.isSuccessful) {
                    return@withContext ResultTest.Success(mapper.toVolumeToken(response.body()!!))
                } else {
                    return@withContext ResultTest.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultTest.Error(e)
            }
        }
}