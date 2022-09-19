package com.appsirise.pixabayexampleapp.photos.data.model

import com.google.gson.annotations.SerializedName

data class SearchedPhotoDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("previewURL")
    val previewUrl: String?,
    @SerializedName("largeImageURL")
    val largeImageUrl: String?,
    @SerializedName("user")
    val userName: String?,
    @SerializedName("tags")
    val tags: String?,
    @SerializedName("likes")
    val likes: Long?,
    @SerializedName("comments")
    val comments: Long?,
    @SerializedName("downloads")
    val downloads: Long?
)
