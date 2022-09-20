package com.appsirise.pixabayexampleapp.photos.ui.repository

import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

internal interface SearchedPhotosSource {
    fun insert(newList: List<SearchedPhoto>): Completable
    fun get(): Flowable<List<SearchedPhoto>>
    fun getById(photoId: Long): Single<SearchedPhoto>
}
