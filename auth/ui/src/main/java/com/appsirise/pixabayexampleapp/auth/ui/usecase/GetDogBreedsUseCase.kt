package com.appsirise.pixabayexampleapp.auth.ui.usecase

import com.appsirise.pixabayexampleapp.auth.data.model.DogBreed
import com.appsirise.pixabayexampleapp.auth.ui.repository.ExampleSource
import javax.inject.Inject

internal class GetDogBreedsUseCase @Inject constructor(
    private val exampleSource: ExampleSource
) {

    suspend fun getBreeds(): List<DogBreed> = exampleSource.getDogBreeds()
}
