package com.appsirise.pixabayexampleapp.photos.ui.model.list

import androidx.annotation.StringRes

sealed class PhotoListEffect {
    data class Error(@StringRes val messageResource: Int) : PhotoListEffect()
}
