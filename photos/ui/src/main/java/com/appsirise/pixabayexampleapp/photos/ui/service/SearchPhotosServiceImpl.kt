package com.appsirise.pixabayexampleapp.photos.ui.service

import com.appsirise.core.ui.extensions.getBodyOrThrowException
import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotosResponse
import com.appsirise.pixabayexampleapp.photos.data.network.SearchPhotosApi
import javax.inject.Inject

/**
 * Best approach to download it from backend
 */
const val RANDOM_STRING = ""

internal class SearchPhotosServiceImpl @Inject constructor(
    private val searchPhotosApi: SearchPhotosApi
) : SearchPhotosService {

    override suspend fun searchPhotos(query: String): SearchedPhotosResponse =
        searchPhotosApi.searchPhotos(RANDOM_STRING, query).getBodyOrThrowException()
}
