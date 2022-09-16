package com.appsirise.pixabayexampleapp.auth.ui.di

import com.appsirise.core.ui.qualifier.Main
import com.appsirise.pixabayexampleapp.auth.data.network.ExampleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal class AuthModule {

/* API */

    @Main
    @Provides
    fun provideExampleApi(@Main retrofit: Retrofit): ExampleApi = retrofit.create()
}
