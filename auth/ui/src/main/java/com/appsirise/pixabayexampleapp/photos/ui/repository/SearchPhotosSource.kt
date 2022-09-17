package com.appsirise.pixabayexampleapp.photos.ui.repository

import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotosResponse
import io.reactivex.Single

internal interface SearchPhotosSource {
    fun searchPhotos(): Single<SearchedPhotosResponse>
}
