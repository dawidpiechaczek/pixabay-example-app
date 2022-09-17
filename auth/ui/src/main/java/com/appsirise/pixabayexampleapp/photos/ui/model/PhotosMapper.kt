package com.appsirise.pixabayexampleapp.photos.ui

import com.appsirise.pixabayexampleapp.photos.data.model.SearchedPhotoDto
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto

fun SearchedPhotoDto.toSearchedPhoto(): SearchedPhoto =
    SearchedPhoto(
        id = id,
        previewUrl = previewUrl,
        pageURL = pageURL,
        userName = userName,
        tags = tags,
        likes = likes,
        comments = comments,
        downloads = downloads
    )
