package com.appsirise.pixabayexampleapp.photos.ui.repository

import com.appsirise.core.ui.extensions.getBodyOrThrowException
import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotosResponse
import com.appsirise.pixabayexampleapp.photos.data.network.SearchPhotosApi
import io.reactivex.Single
import javax.inject.Inject

const val API_KEY = "29991143-f0047d740f3fb47d1ec4120b7"

internal class SearchPhotosServiceImpl @Inject constructor(
    private val searchPhotosApi: SearchPhotosApi
) : SearchPhotosService {

    override fun searchPhotos(): Single<SearchedPhotosResponse> =
        searchPhotosApi.searchPhotos(API_KEY, "fruits")
            .retry(1)
            .map { it.getBodyOrThrowException() }
}
