package repository.paymentRepository

import com.example.data.api.TestApi
import com.example.data.mappers.PaymentsApiResponseMapper
import com.example.domain.common.ResultTest
import com.example.domain.entity.payment.Payments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PaymentsRemoteSourceImpl(
    private val service : TestApi,
    private val mapper : PaymentsApiResponseMapper
) : PaymentsRemoteSource {
    override suspend fun getPaymentsList(token: String): ResultTest<Payments> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPayments(token = token)
                if (response.isSuccessful) {

                    return@withContext ResultTest.Success(mapper.toVolumePaymentsCorrectData(response.body()!!))
                } else {
                    return@withContext ResultTest.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultTest.Error(e)
            }
        }
}