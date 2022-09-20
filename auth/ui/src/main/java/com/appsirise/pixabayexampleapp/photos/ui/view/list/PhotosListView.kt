package com.appsirise.pixabayexampleapp.photos.ui.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.appsirise.core.ui.base.BaseView
import com.appsirise.core.ui.extensions.disposeWith
import com.appsirise.core.ui.utils.PixabayTextWatcher
import com.appsirise.pixabayexampleapp.photos.ui.R
import com.appsirise.pixabayexampleapp.photos.ui.adapter.SearchedPhotosAdapter
import com.appsirise.pixabayexampleapp.photos.ui.databinding.FragmentPhotosListBinding
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

private const val DEBOUNCE_INTERVAL = 1000L
private const val DEFAULT_MAJOR_LETTERS_TO_SEARCH = 2

internal class PhotosListView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<PhotosListView.Listener, FragmentPhotosListBinding>(
        layoutInflater, parent, R.layout.fragment_photos_list
    ) {

    interface Listener {
        fun onClickNavigateToPhotoDetails(photoId: Long)
        fun onSearchQueryChanged(query: String)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val debouncedQueryChange: PublishSubject<String> = PublishSubject.create()
    private val photosAdapter: SearchedPhotosAdapter =
        SearchedPhotosAdapter { navigateToPhotoDetails(it.getUniqueId()) }
    private val queryWatcher =
        PixabayTextWatcher {
            if (it.length > DEFAULT_MAJOR_LETTERS_TO_SEARCH)
                debouncedQueryChange.onNext(it)
        }

    init {
        photosAdapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }.also { binding.photosAdapter = it }

        debouncedQueryChange
            .debounce(DEBOUNCE_INTERVAL, TimeUnit.MILLISECONDS)
            .subscribe { onTextChangeListener(it) }
            .disposeWith(compositeDisposable)
    }

    private fun navigateToPhotoDetails(photoId: Long) {
        listeners.forEach { listener -> listener.onClickNavigateToPhotoDetails(photoId) }
    }

    private fun onTextChangeListener(query: String) {
        listeners.forEach { listener -> listener.onSearchQueryChanged(query) }
    }

    fun bindPhotos(photos: List<SearchedPhoto>) {
        photosAdapter.apply {
            submitList(photos)
            notifyDataSetChanged()
        }
    }

    fun showError(errorMessage: Int) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    fun registerTextListeners() {
        binding.search.addTextChangedListener(queryWatcher)
    }

    fun unregisterTextListeners() {
        binding.search.removeTextChangedListener(queryWatcher)
    }
}
