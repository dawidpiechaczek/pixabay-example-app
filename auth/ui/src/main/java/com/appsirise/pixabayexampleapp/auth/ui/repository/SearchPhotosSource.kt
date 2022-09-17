package com.appsirise.pixabayexampleapp.auth.ui.repository

import com.appsirise.pixabayexampleapp.auth.data.model.SearchedPhotosResponse

internal interface SearchPhotosSource {
    suspend fun searchPhotos(): SearchedPhotosResponse
}
