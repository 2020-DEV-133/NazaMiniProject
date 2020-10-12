package com.debin.androidminiproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debin.androidminiproject.datasource.data.Photos
import com.debin.androidminiproject.datasource.repository.PhotosRepository
import com.debin.androidminiproject.ui.PhotosActivity.PhotoResultStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PhotosViewModel(private val photosRepository: PhotosRepository) : ViewModel() {
    private var _photosResultStatus = MutableLiveData<PhotoResultStatus>()
    val photoResultStatus : LiveData<PhotoResultStatus>
    get() = _photosResultStatus

    private val uiScope = CoroutineScope(Dispatchers.Main)

    init {
        uiScope.launch {
         _photosResultStatus.postValue(PhotoResultStatus.Loading(true))
            var result = photosRepository.getAllPhotos("1000", "vr0uYHrB8KnD05DSMH3AHVrj2cjrYxq3aqcXLcQ0")
            result.observeForever {
                _photosResultStatus.postValue(PhotoResultStatus.Loading(false))
                if(it is Photos) {
                    _photosResultStatus.postValue(PhotoResultStatus.Success(it.photos))
                } else {
                    _photosResultStatus.postValue(PhotoResultStatus.Error(it.toString()))
                }
            }
        }
    }
}