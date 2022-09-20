package com.appsirise.pixabayexampleapp.photos.ui.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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
        fun onClickNavigateToPhotoDetails(photoId: Long)
    }

    var photosAdapter: SearchedPhotosAdapter =
        SearchedPhotosAdapter { navigateToPhotoDetails(it.getUniqueId()) }

    init {
        photosAdapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }.also { binding.photosAdapter = it }
    }

    private fun navigateToPhotoDetails(photoId: Long) {
        listeners.forEach { listener ->
            listener.onClickNavigateToPhotoDetails(photoId)
        }
    }

    fun bindPhotos(photos: List<SearchedPhoto>) {
        photosAdapter.submitList(photos)
    }

    fun showError(errorMessage: Int) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
