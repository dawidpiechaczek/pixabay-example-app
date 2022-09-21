package com.appsirise.pixabayexampleapp.photos.ui.di

import com.appsirise.pixabayexampleapp.photos.ui.model.details.PhotoDetailsEffect
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.subjects.PublishSubject

@Module
@InstallIn(SingletonComponent::class)
internal class PhotoDetailsModule {

    @Provides
    fun provideSearchPhotosEffect(): PublishSubject<PhotoDetailsEffect> = PublishSubject.create()
}
