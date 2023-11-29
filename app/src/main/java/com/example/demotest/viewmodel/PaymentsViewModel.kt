package com.example.demotest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultTest
import com.example.domain.entity.Payments
import com.example.domain.usecase.GetPaymentsListUseCase
import kotlinx.coroutines.launch

class PaymentsViewModel(
    private val getPaymentsListUseCase: GetPaymentsListUseCase
) : ViewModel()
{
    private val _payments = MutableLiveData<Payments?>()
    val payments: LiveData<Payments?> get() = _payments

    private val _errorPayments = MutableLiveData<String>()
    val errorPayments: LiveData<String> = _errorPayments

    fun getPayments(token : String) {
        viewModelScope.launch {
            when (val tokenResult = getPaymentsListUseCase.invoke(token = token)) {
                is ResultTest.Success -> {
                    _payments.value = tokenResult.data
                }

                is ResultTest.Error -> {
                    _errorPayments.postValue(tokenResult.exception.message)
                }
            }
        }
    }
}