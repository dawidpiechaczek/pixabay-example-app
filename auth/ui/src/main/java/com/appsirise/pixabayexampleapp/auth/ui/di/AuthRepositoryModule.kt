package com.appsirise.pixabayexampleapp.auth.ui.di

import com.appsirise.pixabayexampleapp.auth.ui.repository.ExampleRepository
import com.appsirise.pixabayexampleapp.auth.ui.repository.ExampleSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthRepositoryModule {

    @Binds
    abstract fun bindExampleRepository(exampleRepository: ExampleRepository): ExampleSource
}
