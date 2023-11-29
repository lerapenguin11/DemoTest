package repository.paymentRepository

import com.example.domain.common.ResultTest
import com.example.domain.entity.payment.Payments

interface PaymentsRemoteSource{
    suspend fun getPaymentsList(token : String): ResultTest<Payments>
}