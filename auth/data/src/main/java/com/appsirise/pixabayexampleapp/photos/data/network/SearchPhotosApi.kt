package com.appsirise.pixabayexampleapp.photos.data.network

import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotosResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchPhotosApi {

    @GET("/api")
    fun searchPhotos(
        @Query("key") key: String,
        @Query("q") searchQuery: String
    ): Single<Response<SearchedPhotosResponse>>
}
