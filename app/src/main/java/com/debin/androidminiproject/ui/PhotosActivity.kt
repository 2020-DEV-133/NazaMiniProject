package com.debin.androidminiproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.debin.androidminiproject.R
import com.debin.androidminiproject.adapter.PhotosAdapter
import com.debin.androidminiproject.databinding.ActivityPhotosBinding
import com.debin.androidminiproject.datasource.data.Photo
import com.debin.androidminiproject.datasource.data.Photos
import com.debin.androidminiproject.datasource.repository.PhotosRepository
import com.debin.androidminiproject.datasource.repository.PhotosRepositoryImpl
import com.debin.androidminiproject.viewmodel.PhotosViewModel
import com.debin.androidminiproject.viewmodel.PhotosViewModelFactory
import kotlinx.android.synthetic.main.activity_photos.*

class PhotosActivity : AppCompatActivity() {

    private lateinit var viewModel : PhotosViewModel
    private lateinit var binding: ActivityPhotosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos)
        val photosViewModelFactory = PhotosViewModelFactory(PhotosRepositoryImpl())
        viewModel = ViewModelProvider(this, photosViewModelFactory).get(PhotosViewModel::class.java)
        binding.photosViewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvPhotos.layoutManager = GridLayoutManager(this, 2)
        subscribeObserver()

    }

    private fun subscribeObserver() {
        viewModel.photoResultStatus.observe(this, Observer { state ->
            when(state) {
                is PhotoResultStatus.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is PhotoResultStatus.Success -> {
                    progressBar.visibility = View.GONE
                    binding.rvPhotos.adapter = PhotosAdapter(state.photosResponse)
                }
                is PhotoResultStatus.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, state.errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }



sealed class PhotoResultStatus {
    data class Loading(var loading: Boolean) : PhotoResultStatus()
    data class Success(var photosResponse: List<Photo>) : PhotoResultStatus()
    data class Error(var errorMsg: String) : PhotoResultStatus()
}
}