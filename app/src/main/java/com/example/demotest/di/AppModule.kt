package com.example.demotest.di

import com.example.demotest.viewmodel.PaymentsViewModel
import com.example.demotest.viewmodel.UserViewModel
import com.example.domain.usecase.GetLoginUseCase
import com.example.domain.usecase.GetPaymentsListUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<UserViewModel> {
        UserViewModel(
            getLoginUseCase = GetLoginUseCase(get())
        )
    }

    viewModel<PaymentsViewModel> {
        PaymentsViewModel(
             getPaymentsListUseCase = GetPaymentsListUseCase(get())
        )
    }
}