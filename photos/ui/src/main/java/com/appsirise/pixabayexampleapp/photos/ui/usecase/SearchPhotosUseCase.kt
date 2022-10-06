package com.appsirise.pixabayexampleapp.photos.ui.usecase

import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import com.appsirise.pixabayexampleapp.photos.ui.model.toSearchedPhoto
import com.appsirise.pixabayexampleapp.photos.ui.service.SearchPhotosService
import javax.inject.Inject

internal class SearchPhotosUseCase @Inject constructor(
    private val searchPhotosService: SearchPhotosService,
) {

    suspend fun searchPhotos(query: String): List<SearchedPhoto> =
        searchPhotosService.searchPhotos(query)
            .searchedPhotos.map { it.toSearchedPhoto() }
}
