package com.appsirise.pixabayexampleapp.photos.ui.view.details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appsirise.core.ui.base.BaseView
import com.appsirise.pixabayexampleapp.photos.ui.R
import com.appsirise.pixabayexampleapp.photos.ui.databinding.FragmentPhotosDetailsBinding
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto

internal class PhotosDetailsView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<PhotosDetailsView.Listener, FragmentPhotosDetailsBinding>(
        layoutInflater, parent, R.layout.fragment_photos_details
    ) {

    fun setPhotoDetails(searchPhoto: SearchedPhoto?) {
        if (searchPhoto != null) {
            binding.searchedPhoto = searchPhoto
        }
    }

    interface Listener {
        fun onClickNavigateBack()
    }

    init { }
}
