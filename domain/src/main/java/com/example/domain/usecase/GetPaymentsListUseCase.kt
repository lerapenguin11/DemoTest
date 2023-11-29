package com.example.domain.usecase

import com.example.domain.repository.PaymentsRepository

class GetPaymentsListUseCase(private val repository: PaymentsRepository) {

    suspend operator fun invoke(token : String) = repository.getPaymentsList(token = token)
}