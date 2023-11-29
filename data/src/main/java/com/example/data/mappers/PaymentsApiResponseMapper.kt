package com.example.data.mappers

import com.example.data.api.model.PaymentsApiResponse
import com.example.domain.entity.Payments
import com.example.domain.entity.ResponseX

class PaymentsApiResponseMapper {

    fun toVolumePayments(resp: PaymentsApiResponse): Payments {
        val paymentsList = arrayListOf<ResponseX>()
        for (i in resp.response) {
            val amount = i.amount
            val created = i.created
            val id = i.id
            val title = i.title

            val payments = ResponseX(
                amount = amount,
                created = created,
                id = id,
                title = title
            )
            paymentsList.add(payments)
        }
        return Payments(response = paymentsList, success = resp.success)
    }
}