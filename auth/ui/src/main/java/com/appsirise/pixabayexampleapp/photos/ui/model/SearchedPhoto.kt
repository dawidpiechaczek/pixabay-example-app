package com.appsirise.pixabayexampleapp.photos.ui.model

data class SearchedPhoto(
    val id: Long,
    val previewUrl: String?,
    val pageURL: String?,
    val userName: String?,
    val tags: String?,
    val likes: Long?,
    val comments: Long?,
    val downloads: Long?
)
