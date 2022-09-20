package com.appsirise.pixabayexampleapp.photos.ui.view.details

import androidx.lifecycle.ViewModel
import com.appsirise.pixabayexampleapp.photos.ui.model.details.PhotoDetailsAction
import com.appsirise.pixabayexampleapp.photos.ui.model.details.PhotoDetailsEffect
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosSource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

abstract class PhotosDetailsViewModel : ViewModel() {
    abstract fun observeEffect(): Observable<PhotoDetailsEffect>
    abstract fun onAction(action: PhotoDetailsAction): Completable
}

@HiltViewModel
internal class PhotosDetailsViewModelImpl @Inject constructor(
    private val searchedPhotosSource: SearchedPhotosSource,
    private val effect: PublishSubject<PhotoDetailsEffect>
) : PhotosDetailsViewModel() {

    override fun onAction(action: PhotoDetailsAction): Completable = when (action) {
        is PhotoDetailsAction.GetPhotoDetails -> getPhotoDetails(action.photoId)
    }

    private fun getPhotoDetails(photoId: Long): Completable =
        searchedPhotosSource.getById(photoId)
            .flatMapCompletable {
                effect.onNext(PhotoDetailsEffect.PhotoDetails(it))
                Completable.complete()
            }

    override fun observeEffect(): Observable<PhotoDetailsEffect> = effect.hide()
}
