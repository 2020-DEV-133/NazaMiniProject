package com.debin.androidminiproject.datasource.repository

import androidx.lifecycle.LiveData


interface PhotosRepository {
     suspend fun getAllPhotos(sol : String, key : String) : LiveData<Any>
}