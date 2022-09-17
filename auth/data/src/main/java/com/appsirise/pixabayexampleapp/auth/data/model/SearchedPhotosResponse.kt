package com.appsirise.pixabayexampleapp.auth.data.model

import com.google.gson.annotations.SerializedName

data class SearchedPhotosResponse(
    @SerializedName("total")
    val total: Long,
    @SerializedName("totalHits")
    val totalHits: Long,
    @SerializedName("hits")
    val searchedPhotos: List<SearchedPhotoDto>
)
