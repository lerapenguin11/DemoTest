package com.example.data.api

import com.example.data.api.model.PaymentsApiResponse
import com.example.data.api.model.TokenResponse
import com.example.data.api.model.UserRequest
import retrofit2.Response
import retrofit2.http.*

interface TestApi {
    @Headers("app-key: 12345", "v: 1")
    @POST("login")
    suspend fun getLogin(@Body requestBody: UserRequest): Response<TokenResponse>

    @Headers("app-key: 12345", "v: 1")
    @GET("payments")
    suspend fun getPayments( @Header("token") token: String): Response<PaymentsApiResponse>
}