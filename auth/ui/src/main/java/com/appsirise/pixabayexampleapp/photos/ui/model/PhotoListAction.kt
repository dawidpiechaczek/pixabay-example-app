package com.appsirise.pixabayexampleapp.photos.ui.model

sealed class PhotoListAction {
    data class SearchPhotos(val searchQuery: String) : PhotoListAction()
    data class GetCachedPhotos(val isInitial: Boolean) : PhotoListAction()
    data class GetPhotoDetails(val photoId: Long, val lastRecyclerPosition: Int) : PhotoListAction()
}
