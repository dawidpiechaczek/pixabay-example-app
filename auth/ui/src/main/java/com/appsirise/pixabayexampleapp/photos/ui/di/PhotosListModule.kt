package com.appsirise.pixabayexampleapp.photos.ui.di

import com.appsirise.pixabayexampleapp.photos.ui.model.list.PhotoListEffect
import com.appsirise.pixabayexampleapp.photos.ui.model.list.PhotoListState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

@Module
@InstallIn(SingletonComponent::class)
internal class PhotosListModule {

    @Provides
    fun provideSearchPhotosEffect(): PublishSubject<PhotoListEffect> = PublishSubject.create()

    @Provides
    fun provideSearchPhotosState(): BehaviorSubject<PhotoListState> =
        BehaviorSubject.createDefault(PhotoListState(searchedPhotos = listOf()))
}
