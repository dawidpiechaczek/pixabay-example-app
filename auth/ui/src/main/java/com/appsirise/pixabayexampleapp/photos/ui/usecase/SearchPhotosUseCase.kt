package com.appsirise.pixabayexampleapp.photos.ui.usecase

import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchPhotosSource
import com.appsirise.pixabayexampleapp.photos.ui.model.toSearchedPhoto
import io.reactivex.Single
import javax.inject.Inject

internal class SearchPhotosUseCase @Inject constructor(
    private val searchPhotosSource: SearchPhotosSource
) {

    fun searchPhotos(): Single<List<SearchedPhoto>> =
        searchPhotosSource.searchPhotos()
            .map { response ->
                response.searchedPhotos.map { it.toSearchedPhoto() }
            }
}
