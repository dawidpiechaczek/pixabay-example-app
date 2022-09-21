package com.appsirise.pixabayexampleapp.photos.ui.service

import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotosResponse
import io.reactivex.Single

interface SearchPhotosService {
    fun searchPhotos(query: String): Single<SearchedPhotosResponse>
}