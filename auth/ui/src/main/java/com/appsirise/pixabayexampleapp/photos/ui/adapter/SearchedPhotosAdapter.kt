package com.appsirise.pixabayexampleapp.photos.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.appsirise.core.ui.utils.GenericDiffCallback
import com.appsirise.pixabayexampleapp.photos.ui.databinding.ItemSearchedPhotoBinding
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto

class SearchedPhotosAdapter(
    private val listener: (SearchedPhoto) -> Unit
) : ListAdapter<SearchedPhoto, SearchedPhotoViewHolder>(
    GenericDiffCallback<SearchedPhoto>()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedPhotoViewHolder =
        SearchedPhotoViewHolder(parent, ItemSearchedPhotoBinding::inflate, listener)

    override fun onBindViewHolder(holder: SearchedPhotoViewHolder, position: Int) =
        holder.bind(getItem(position))
}
