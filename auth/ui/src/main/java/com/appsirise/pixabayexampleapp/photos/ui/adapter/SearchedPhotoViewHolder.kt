package com.appsirise.pixabayexampleapp.photos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appsirise.core.ui.base.BaseViewHolder
import com.appsirise.pixabayexampleapp.photos.ui.databinding.ItemSearchedPhotoBinding
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto

class SearchedPhotoViewHolder(
    parent: ViewGroup,
    creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> ItemSearchedPhotoBinding,
    private val listener: (SearchedPhoto) -> Unit
) : BaseViewHolder<ItemSearchedPhotoBinding, SearchedPhoto>(parent, creator) {

    override fun bind(item: SearchedPhoto) {
        binding.searchedPhoto = item
    }
}