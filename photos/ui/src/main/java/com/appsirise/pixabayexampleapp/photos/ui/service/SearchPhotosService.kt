package com.appsirise.pixabayexampleapp.photos.ui.service

import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotosResponse

interface SearchPhotosService {
    suspend fun searchPhotos(query: String): SearchedPhotosResponse
}
