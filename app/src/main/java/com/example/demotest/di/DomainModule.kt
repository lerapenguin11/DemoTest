package com.example.demotest.di

import com.example.domain.usecase.GetLoginUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetLoginUseCase> { GetLoginUseCase(
        repository = get()
    ) }
}