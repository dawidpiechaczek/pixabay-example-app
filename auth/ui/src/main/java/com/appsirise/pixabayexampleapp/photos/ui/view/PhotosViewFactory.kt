package com.appsirise.pixabayexampleapp.photos.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appsirise.pixabayexampleapp.photos.ui.view.details.PhotosDetailsView
import com.appsirise.pixabayexampleapp.photos.ui.view.list.PhotosListView
import javax.inject.Inject
import javax.inject.Provider

class PhotosViewFactory @Inject constructor(private val layoutInflaterProvider: Provider<LayoutInflater>) {

    fun searchedPhotosListView(parent: ViewGroup?): PhotosListView =
        PhotosListView(layoutInflaterProvider.get(), parent)

    fun searchedPhotoDetailsView(parent: ViewGroup?): PhotosDetailsView =
        PhotosDetailsView(layoutInflaterProvider.get(), parent)
}
