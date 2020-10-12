package com.debin.androidminiproject.datasource.network

import com.debin.androidminiproject.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

var client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit.Builder()
    .client(client)
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

object RetrofitClient {
    val retrofitInstance : PhotoService by lazy {
        retrofit.create(PhotoService::class.java)
    }
}