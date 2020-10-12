package com.debin.androidminiproject.datasource.network

import com.debin.androidminiproject.datasource.data.Photo
import com.debin.androidminiproject.datasource.data.Photos
import com.debin.androidminiproject.utils.Constants.SECOND_URL
import com.debin.androidminiproject.utils.StateResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET(SECOND_URL)
    fun getAllPhotos(@Query("sol")  sol : String,
                      @Query("api_key") key : String) : Deferred<StateResponse<Photos>>
}