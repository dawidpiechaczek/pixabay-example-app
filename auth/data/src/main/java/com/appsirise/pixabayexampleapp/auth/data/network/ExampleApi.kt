package com.appsirise.pixabayexampleapp.auth.data.network

import com.appsirise.pixabayexampleapp.auth.data.model.DogBreed
import retrofit2.Response
import retrofit2.http.GET

interface ExampleApi {

    @GET("/v1/breeds?limit=10&page=0")
    suspend fun getDogBreeds(): Response<List<DogBreed>>
}
