package com.appsirise.pixabayexampleapp.photos.ui.repository

import com.appsirise.core.ui.exceptions.PhotoDetailsNotFoundException
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SearchedPhotosRepository @Inject constructor() : SearchedPhotosSource {

    private val _searchedPhotos: MutableSharedFlow<List<SearchedPhoto>> =
        MutableSharedFlow(replay = 1)
    private val searchedPhotos: SharedFlow<List<SearchedPhoto>> = _searchedPhotos

    override suspend fun get(): SharedFlow<List<SearchedPhoto>> = searchedPhotos

    override suspend fun insert(newList: List<SearchedPhoto>) {
        _searchedPhotos.emit(newList)
    }

    override suspend fun getById(photoId: Long): SearchedPhoto =
        searchedPhotos.firstOrNull()?.first { photoId == it.id }
            ?: throw PhotoDetailsNotFoundException()
}
