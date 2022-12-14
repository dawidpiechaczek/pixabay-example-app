package com.appsirise.pixabayexampleapp.photos.ui.model.list

sealed class PhotoListAction {
    data class SearchPhotos(val searchQuery: String) : PhotoListAction()
    data class GetCachedPhotos(val isInitial: Boolean) : PhotoListAction()
}
