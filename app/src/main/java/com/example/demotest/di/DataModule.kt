package com.example.demotest.di

import com.example.data.api.NetworkModule
import com.example.data.mappers.PaymentsApiResponseMapper
import com.example.data.mappers.UserApiResponseMapper
import com.example.demotest.utilits.URL
import com.example.domain.repository.PaymentsRepository
import com.example.domain.repository.UserRepository
import org.koin.dsl.module
import repository.paymentRepository.PaymentsRemoteSource
import repository.paymentRepository.PaymentsRemoteSourceImpl
import repository.paymentRepository.PaymentsRepositoryImpl
import repository.userRepository.UserRemoteSource
import repository.userRepository.UserRemoteSourceImpl
import repository.userRepository.UserRepositoryImpl

val dataModule = module {
    val networkModule by lazy {
        NetworkModule()
    }
    single<UserRemoteSource> { UserRemoteSourceImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { UserApiResponseMapper() }

    single<PaymentsRemoteSource> { PaymentsRemoteSourceImpl(get(), get()) }
    single<PaymentsRepository> { PaymentsRepositoryImpl(get()) }
    single { PaymentsApiResponseMapper() }

    single { networkModule.createApi(URL) }
}