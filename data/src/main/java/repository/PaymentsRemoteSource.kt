package repository

import com.example.domain.common.ResultTest
import com.example.domain.entity.Payments

interface PaymentsRemoteSource{
    suspend fun getPaymentsList(token : String): ResultTest<Payments>
}