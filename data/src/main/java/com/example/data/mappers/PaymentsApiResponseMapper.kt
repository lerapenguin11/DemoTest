package com.example.data.mappers

import android.annotation.SuppressLint
import com.example.data.api.model.payment.PaymentsApiResponse
import com.example.domain.entity.payment.Payments
import com.example.domain.entity.payment.ResponseX
import java.text.SimpleDateFormat
import java.util.*

class PaymentsApiResponseMapper {
    fun toVolumePaymentsTransformationData(resp: PaymentsApiResponse): Payments {
        val paymentsList = arrayListOf<ResponseX>()
        for (i in resp.response) {
            val amount = when (val amountValue = i.amount) {
                is Double -> amountValue
                is String -> amountValue.toDoubleOrNull()
                is Int -> amountValue.toDouble()
                else -> CONST_DOUBLE
            }
            val created = when(val createdValue = i.created){
                is Long -> toVolumeCreateDate(createdValue)
                else -> CONST_STRING
            }
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

    fun toVolumePaymentsCorrectData(resp: PaymentsApiResponse): Payments {
        val paymentsList = arrayListOf<ResponseX>()
        for (i in resp.response){
            if (i.amount is Double && i.created is Long) {
                val responseX = ResponseX(
                    amount = i.amount,
                    created = toVolumeCreateDate(i.created),
                    id = i.id,
                    title = i.title)
                paymentsList.add(responseX)
            }
        }
        return Payments(response = paymentsList, success = resp.success)
    }

    @SuppressLint("SimpleDateFormat")
    private fun toVolumeCreateDate(created: Long?): String? {
        val timestamp = created?.times(TIME_STAMP)
        val date = timestamp?.let { Date(it) }
        val format = SimpleDateFormat(DATE_FORMAT)
        return date?.let { format.format(it) }
    }

    companion object{
        private const val DATE_FORMAT = "dd.MM.yyyy HH:mm:ss"
        private const val CONST_STRING = "Нет данных"
        private const val CONST_DOUBLE = 0.0
        private const val TIME_STAMP = 1000
    }
}