package com.appsirise.pixabayexampleapp.auth.ui.repository

import com.appsirise.pixabayexampleapp.auth.data.model.DogBreed

internal interface ExampleSource {
    suspend fun getDogBreeds(): List<DogBreed>
}
