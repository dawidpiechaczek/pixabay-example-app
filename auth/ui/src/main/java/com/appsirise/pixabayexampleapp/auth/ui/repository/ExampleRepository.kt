package com.appsirise.pixabayexampleapp.auth.ui.repository

import com.appsirise.core.ui.extensions.getBodyOrThrowException
import com.appsirise.core.ui.qualifier.Main
import com.appsirise.pixabayexampleapp.auth.data.model.DogBreed
import com.appsirise.pixabayexampleapp.auth.data.network.ExampleApi
import javax.inject.Inject

internal class ExampleRepository @Inject constructor(
    @Main private val exampleApi: ExampleApi
) : ExampleSource {

    override suspend fun getDogBreeds(): List<DogBreed> =
        exampleApi.getDogBreeds().getBodyOrThrowException()
}
