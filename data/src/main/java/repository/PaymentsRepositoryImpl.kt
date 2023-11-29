package repository

import com.example.domain.common.ResultTest
import com.example.domain.entity.Payments
import com.example.domain.repository.PaymentsRepository

class PaymentsRepositoryImpl(
    private val remoteSource : PaymentsRemoteSource
) : PaymentsRepository{
    override suspend fun getPaymentsList(token: String): ResultTest<Payments> {
        return remoteSource.getPaymentsList(token = token)
    }
}