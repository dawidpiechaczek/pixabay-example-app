package com.appsirise.pixabayexampleapp.photos.ui.view.list

import androidx.lifecycle.ViewModel
import com.appsirise.core.ui.utils.ErrorMessageHelper
import com.appsirise.pixabayexampleapp.photos.ui.model.PhotoListAction
import com.appsirise.pixabayexampleapp.photos.ui.model.PhotoListEffect
import com.appsirise.pixabayexampleapp.photos.ui.model.PhotoListState
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosSource
import com.appsirise.pixabayexampleapp.photos.ui.usecase.SearchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

abstract class PhotosListViewModel : ViewModel() {
    abstract fun observeEffect(): Observable<PhotoListEffect>
    abstract fun observeState(): Observable<PhotoListState>
    abstract fun onAction(action: PhotoListAction): Completable
}

@HiltViewModel
internal class PhotosListViewModelImpl @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val searchedPhotosSource: SearchedPhotosSource,
    private val effect: PublishSubject<PhotoListEffect>,
    private val state: BehaviorSubject<PhotoListState>
) : PhotosListViewModel() {

    override fun onAction(action: PhotoListAction): Completable = when (action) {
        is PhotoListAction.SearchPhotos -> searchPhotosCompletable()
        is PhotoListAction.GetCachedPhotos -> getCachedPhotos(action.isInitial)
    }

    private fun getCachedPhotos(isInitial: Boolean = false): Completable =
        searchedPhotosSource.get()
            .flatMapCompletable {
                if (isInitial && it.isEmpty()) {
                    searchPhotosCompletable()
                } else {
                    state.onNext(state.value.copy(searchedPhotos = it))
                    Completable.complete()
                }
            }

    private fun searchPhotosCompletable(): Completable =
        searchPhotosUseCase.searchPhotos()
            .doOnError {
                effect.onNext(PhotoListEffect.Error(ErrorMessageHelper(it).getMessageStringId()))
                Timber.e(it)
            }
            .andThen(getCachedPhotos())

    override fun observeEffect(): Observable<PhotoListEffect> = effect.hide()

    override fun observeState(): Observable<PhotoListState> = state.hide()
}
