package com.example.demotest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultTest
import com.example.domain.entity.Token
import com.example.domain.entity.User
import com.example.domain.usecase.GetLoginUseCase
import kotlinx.coroutines.launch

class UserViewModel(
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel(){

    private val _token = MutableLiveData<Token?>()
    val token: LiveData<Token?> get() = _token

    fun login(login: String, password: String) {
        viewModelScope.launch {
            /*
            val token = getLoginUseCase.invoke(user)
            _token.value = token*/
            val user = User(login, password)
            when (val tokenResult = getLoginUseCase.invoke(user)) {
                is ResultTest.Success -> {
                    _token.value = tokenResult.data
                }

                is ResultTest.Error -> {
                    //_errorHotel.postValue(tokenResult.exception.message)
                    _token.value = null
                }
            }
        }
    }
}