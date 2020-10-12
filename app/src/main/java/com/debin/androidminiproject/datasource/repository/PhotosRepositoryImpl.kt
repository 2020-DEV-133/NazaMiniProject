package com.debin.androidminiproject.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.debin.androidminiproject.datasource.network.PhotoService
import com.debin.androidminiproject.datasource.network.RetrofitClient

class PhotosRepositoryImpl : PhotosRepository {

    private var _photosResponse = MutableLiveData<Any>()
    val photosRespons : LiveData<Any>
    get() = _photosResponse

    override suspend fun getAllPhotos(sol: String, key: String): LiveData<Any> {
      val result = RetrofitClient.retrofitInstance.getAllPhotos(sol, key)
        _photosResponse.postValue(result.await())
      return photosRespons
    }
}