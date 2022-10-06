package com.appsirise.pixabayexampleapp.photos.data.network

import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchPhotosApi {

    @GET("/api")
    suspend fun searchPhotos(
        @Query("key") key: String,
        @Query("q") searchQuery: String
    ): Response<SearchedPhotosResponse>
}
