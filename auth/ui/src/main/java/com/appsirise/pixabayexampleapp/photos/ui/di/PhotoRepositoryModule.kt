package com.appsirise.pixabayexampleapp.photos.ui.di

import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchPhotosService
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchPhotosServiceImpl
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosRepository
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PhotoRepositoryModule {

    @Binds
    abstract fun bindSearchPhotosService(searchPhotosServiceImpl: SearchPhotosServiceImpl): SearchPhotosService

    @Binds
    @Singleton
    abstract fun bindSearchPhotosRepository(searchedPhotosRepository: SearchedPhotosRepository): SearchedPhotosSource
}
