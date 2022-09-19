package com.appsirise.pixabayexampleapp.photos.ui.model

import androidx.annotation.StringRes

sealed class PhotoListEffect {
    data class Error(@StringRes val messageResource: Int) : PhotoListEffect()
    data class NavigateToPhotoDetails(val photoId: Long) : PhotoListEffect()
}
