package com.appsirise.pixabayexampleapp.photos.ui.di

import com.appsirise.pixabayexampleapp.photos.ui.PhotosListViewModel
import com.appsirise.pixabayexampleapp.photos.ui.PhotosListViewModelImpl
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchPhotosRepository
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchPhotosSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthRepositoryModule {

    @Binds
    abstract fun bindExampleRepository(exampleRepository: SearchPhotosRepository): SearchPhotosSource

    @Binds
    abstract fun bindPhotosListViewModel(photosListViewModelImpl: PhotosListViewModelImpl): PhotosListViewModel
}
