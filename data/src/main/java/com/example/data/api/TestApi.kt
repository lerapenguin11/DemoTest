package com.example.data.api

import com.example.data.api.model.TokenResponse
import com.example.data.api.model.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TestApi {
    //https://easypay.world/api-test/login?app-key=12345&v=1

    @Headers("app-key: 12345", "v: 1")
    @POST("login")
    suspend fun getLogin(@Body requestBody: UserRequest): Response<TokenResponse>
}