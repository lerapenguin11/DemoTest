package com.example.domain.common

sealed class ResultTest<out R> {

    data class Success<out T>(val data: T) : ResultTest<T>()
    data class Error(val exception: Exception) : ResultTest<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}