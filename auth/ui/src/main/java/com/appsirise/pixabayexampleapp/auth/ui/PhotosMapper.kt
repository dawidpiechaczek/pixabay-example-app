package com.appsirise.pixabayexampleapp.auth.ui

import com.appsirise.pixabayexampleapp.auth.data.model.SearchedPhotoDto

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
