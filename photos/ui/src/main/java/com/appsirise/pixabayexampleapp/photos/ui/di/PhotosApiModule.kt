package com.appsirise.pixabayexampleapp.photos.ui.di

import com.appsirise.pixabayexampleapp.photos.data.network.SearchPhotosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal class PhotosApiModule {

    @Provides
    fun provideSearchPhotosApi(retrofit: Retrofit): SearchPhotosApi = retrofit.create()
}
