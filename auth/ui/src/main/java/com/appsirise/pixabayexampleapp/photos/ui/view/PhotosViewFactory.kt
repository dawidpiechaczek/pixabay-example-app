package com.appsirise.pixabayexampleapp.photos.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appsirise.pixabayexampleapp.photos.ui.view.PhotosListView
import javax.inject.Inject
import javax.inject.Provider

class PhotosViewFactory @Inject constructor(private val layoutInflaterProvider: Provider<LayoutInflater>) {

    fun photosListView(parent: ViewGroup?): PhotosListView =
        PhotosListView(layoutInflaterProvider.get(), parent)
}
