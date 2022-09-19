package com.appsirise.pixabayexampleapp.photos.ui.di

import com.appsirise.pixabayexampleapp.photos.data.network.SearchPhotosApi
import com.appsirise.pixabayexampleapp.photos.ui.model.PhotoListEffect
import com.appsirise.pixabayexampleapp.photos.ui.model.PhotoListState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal class AuthModule {

/* API */

    @Provides
    fun provideSearchPhotosApi(retrofit: Retrofit): SearchPhotosApi = retrofit.create()

    @Provides
    fun provideSearchPhotosEffect(): PublishSubject<PhotoListEffect> = PublishSubject.create()

    @Provides
    fun provideSearchPhotosState(): BehaviorSubject<PhotoListState> =
        BehaviorSubject.createDefault(PhotoListState(searchedPhotos = listOf()))
}
