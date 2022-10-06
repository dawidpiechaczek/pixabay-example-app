package com.appsirise.pixabayexampleapp.photos.ui.repository

import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import kotlinx.coroutines.flow.SharedFlow

internal interface SearchedPhotosSource {
    suspend fun insert(newList: List<SearchedPhoto>)
    suspend fun get(): SharedFlow<List<SearchedPhoto>>
    suspend fun getById(photoId: Long): SearchedPhoto
}
