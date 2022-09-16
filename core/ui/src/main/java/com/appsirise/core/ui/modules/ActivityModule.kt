package com.appsirise.core.ui.modules

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    companion object {
        @Provides
        fun providesLayoutInflater(@ActivityContext context: Context): LayoutInflater =
            LayoutInflater.from(context)

        @Provides
        fun providesFragmentManager(@ActivityContext context: Context) =
            (context as AppCompatActivity).supportFragmentManager
    }
}
