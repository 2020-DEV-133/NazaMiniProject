package com.debin.androidminiproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.debin.androidminiproject.R
import com.debin.androidminiproject.databinding.ItemLayoutPhotosBinding
import com.debin.androidminiproject.datasource.data.Photo


class PhotosAdapter(private val photosList : List<Photo>) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

   inner class PhotosViewHolder(val binding: ItemLayoutPhotosBinding) : RecyclerView.ViewHolder(binding.root){
       fun bindPhotos(photo: Photo) {
           binding.apply {
              Glide.with(binding.root).load(photo.img_src).into(imgPic)
              title = photo.rover.name
               author = photo.camera.full_name
               date = photo.rover.launch_date
           }
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemLayoutPhotosBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_layout_photos, parent,false
        )
        return PhotosViewHolder(binding)

    }

    override fun getItemCount(): Int = photosList.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photo = photosList[position]
        holder.bindPhotos(photo)
    }


}