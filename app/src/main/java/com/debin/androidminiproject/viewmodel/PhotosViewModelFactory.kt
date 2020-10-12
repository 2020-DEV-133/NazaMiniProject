package com.debin.androidminiproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.debin.androidminiproject.datasource.repository.PhotosRepository

class PhotosViewModelFactory(private val photosRepository: PhotosRepository)  : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return PhotosViewModel(photosRepository) as T
    }
}