package com.example.demotest.di

import com.example.domain.usecase.GetLoginUseCase
import com.example.domain.usecase.GetPaymentsListUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetLoginUseCase> { GetLoginUseCase(
        repository = get()
    ) }

    factory<GetPaymentsListUseCase> { GetPaymentsListUseCase(
        repository = get()
    ) }
}