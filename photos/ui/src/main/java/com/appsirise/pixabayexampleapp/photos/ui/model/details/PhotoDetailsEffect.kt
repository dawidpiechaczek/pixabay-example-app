package com.appsirise.pixabayexampleapp.photos.ui.model.details

import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto

sealed class PhotoDetailsEffect {
    data class PhotoDetails(val photoDetails: SearchedPhoto) : PhotoDetailsEffect()
}
