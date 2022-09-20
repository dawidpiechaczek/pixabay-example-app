package com.appsirise.pixabayexampleapp.photos.ui.repository

import com.appsirise.core.ui.extensions.getBodyOrThrowException
import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotosResponse
import com.appsirise.pixabayexampleapp.photos.data.network.SearchPhotosApi
import io.reactivex.Single
import javax.inject.Inject

const val RANDOM_STRING = ""

internal class SearchPhotosServiceImpl @Inject constructor(
    private val searchPhotosApi: SearchPhotosApi
) : SearchPhotosService {

    override fun searchPhotos(query: String): Single<SearchedPhotosResponse> =
        searchPhotosApi.searchPhotos(RANDOM_STRING, query)
            .retry(1)
            .map { it.getBodyOrThrowException() }
}
