package com.appsirise.pixabayexampleapp.photos.ui.model

import com.appsirise.core.ui.utils.UniqueId

data class SearchedPhoto(
    val id: Long,
    val previewUrl: String?,
    val largeImageUrl: String?,
    val userName: String?,
    val tags: String?,
    val likes: Long?,
    val comments: Long?,
    val downloads: Long?
) : UniqueId {

    override fun getUniqueId(): Long = id
}
