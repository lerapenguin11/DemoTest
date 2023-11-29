package com.example.domain.repository

import com.example.domain.common.ResultTest
import com.example.domain.entity.Payments

interface PaymentsRepository {

    suspend fun getPaymentsList(token : String): ResultTest<Payments>
}