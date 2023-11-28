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
    private var _checkToken = MutableLiveData<Boolean>()
    fun login(login: String, password: String) {
        viewModelScope.launch {
            val user = User(login, password)
            when (val tokenResult = getLoginUseCase.invoke(user)) {
                is ResultTest.Success -> {
                    _token.value = tokenResult.data
                }

                is ResultTest.Error -> {
                    _token.value = null
                }
            }
        }
    }

    fun checkToken() : Boolean{
        if (_token.value == null){
            return false
        } else{
            return true
        }
    }
}