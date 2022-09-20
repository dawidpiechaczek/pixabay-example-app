package com.appsirise.pixabayexampleapp.photos.ui.model.details

sealed class PhotoDetailsAction {
    data class GetPhotoDetails(val photoId: Long) : PhotoDetailsAction()
}
