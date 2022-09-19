package com.appsirise.pixabayexampleapp.photos.ui.model

import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotoDto

fun SearchedPhotoDto.toSearchedPhoto(): SearchedPhoto =
    SearchedPhoto(
        id = id,
        previewUrl = previewUrl,
        largeImageUrl = largeImageUrl,
        userName = userName,
        tags = tags,
        likes = likes,
        comments = comments,
        downloads = downloads
    )
