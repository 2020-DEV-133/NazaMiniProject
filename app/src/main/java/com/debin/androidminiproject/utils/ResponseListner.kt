package com.debin.androidminiproject.utils

interface ResponseListner {
    fun onLoading()
    fun onSuccess()
    fun onFailure(errorMessage : String)
}