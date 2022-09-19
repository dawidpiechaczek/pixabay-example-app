package com.appsirise.pixabayexampleapp.photos.ui.repository

import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class SearchedPhotosRepository @Inject constructor() : SearchedPhotosSource {

    private val searchedPhotos: MutableList<SearchedPhoto> = mutableListOf()

    override fun insert(newList: List<SearchedPhoto>): Completable = Completable.fromAction {
        searchedPhotos.clear()
        searchedPhotos.addAll(newList)
    }

    override fun get(): Flowable<List<SearchedPhoto>> = Flowable.just(searchedPhotos)
}
