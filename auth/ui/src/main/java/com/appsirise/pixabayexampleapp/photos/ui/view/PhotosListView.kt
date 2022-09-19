package com.appsirise.pixabayexampleapp.photos.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.appsirise.core.ui.base.BaseView
import com.appsirise.pixabayexampleapp.photos.ui.R
import com.appsirise.pixabayexampleapp.photos.ui.adapter.SearchedPhotosAdapter
import com.appsirise.pixabayexampleapp.photos.ui.databinding.FragmentPhotosListBinding
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto

class PhotosListView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<PhotosListView.Listener, FragmentPhotosListBinding>(
        layoutInflater,
        parent,
        R.layout.fragment_photos_list
    ) {

    interface Listener {
        fun onClickNavigateToDashboard()
    }

    var photosAdapter: SearchedPhotosAdapter = SearchedPhotosAdapter { }

    init {
        binding.photosAdapter = photosAdapter
        binding.navigateToDashboard.setOnClickListener { listeners.forEach { listener -> listener.onClickNavigateToDashboard() } }
    }

    fun bindPhotos(photos: List<SearchedPhoto>) {
        photosAdapter.submitList(photos)
    }

    fun showError(errorMessage: Int) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
