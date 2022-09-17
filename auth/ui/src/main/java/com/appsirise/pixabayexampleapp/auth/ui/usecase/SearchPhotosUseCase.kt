package com.appsirise.pixabayexampleapp.auth.ui.usecase

import com.appsirise.pixabayexampleapp.auth.ui.SearchedPhoto
import com.appsirise.pixabayexampleapp.auth.ui.repository.SearchPhotosSource
import com.appsirise.pixabayexampleapp.auth.ui.toSearchedPhoto
import javax.inject.Inject

internal class SearchPhotosUseCase @Inject constructor(
    private val searchPhotosSource: SearchPhotosSource
) {

    suspend fun searchPhotos(): List<SearchedPhoto> =
        searchPhotosSource.searchPhotos().searchedPhotos.map { it.toSearchedPhoto() }
}
