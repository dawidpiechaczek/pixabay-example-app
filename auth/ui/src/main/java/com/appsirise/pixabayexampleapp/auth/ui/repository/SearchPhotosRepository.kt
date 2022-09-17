package com.appsirise.pixabayexampleapp.auth.ui.repository

import com.appsirise.core.ui.extensions.getBodyOrThrowException
import com.appsirise.pixabayexampleapp.auth.data.model.SearchedPhotosResponse
import com.appsirise.pixabayexampleapp.auth.data.network.SearchPhotosApi
import javax.inject.Inject

const val RANDOM_STRING = ""

internal class SearchPhotosRepository @Inject constructor(
    private val searchPhotosApi: SearchPhotosApi
) : SearchPhotosSource {

    override suspend fun searchPhotos(): SearchedPhotosResponse =
        searchPhotosApi.searchPhotos(RANDOM_STRING, "fruits").getBodyOrThrowException()
}
