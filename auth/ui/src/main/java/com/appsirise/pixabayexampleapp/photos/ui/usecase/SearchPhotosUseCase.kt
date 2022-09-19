package com.appsirise.pixabayexampleapp.photos.ui.usecase

import com.appsirise.pixabayexampleapp.photos.ui.model.toSearchedPhoto
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchPhotosService
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosSource
import io.reactivex.Completable
import javax.inject.Inject

internal class SearchPhotosUseCase @Inject constructor(
    private val searchPhotosService: SearchPhotosService,
    private val searchPhotosSource: SearchedPhotosSource
) {

    fun searchPhotos(): Completable =
        searchPhotosService.searchPhotos()
            .map { response -> response.searchedPhotos.map { it.toSearchedPhoto() } }
            .flatMapCompletable { searchPhotosSource.insert(it) }
}
