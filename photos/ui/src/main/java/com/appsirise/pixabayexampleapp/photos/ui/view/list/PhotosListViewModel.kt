package com.appsirise.pixabayexampleapp.photos.ui.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsirise.core.ui.utils.ErrorMessageHelper
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosSource
import com.appsirise.pixabayexampleapp.photos.ui.usecase.SearchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val DEFAULT_SEARCH_QUERY_VALUE = "fruits"

@HiltViewModel
internal class PhotosListViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val searchedPhotosSource: SearchedPhotosSource
) : ViewModel() {

    init {
        viewModelScope.launch {
            searchedPhotosSource.get().collect { searchedPhotos ->
                photosLiveData.value = searchedPhotos
            }
        }
    }

    private val photosLiveData = MutableLiveData<List<SearchedPhoto>>()

    private val _searchedPhotosLiveData = MutableLiveData<ViewState<List<SearchedPhoto>>>()
    val searchedPhotosLiveData: LiveData<ViewState<List<SearchedPhoto>>> = _searchedPhotosLiveData

    fun getCachedPhotos(isInitial: Boolean = false) {
        val searchedPhotos = photosLiveData.value ?: listOf()
        if (isInitial && searchedPhotos.isEmpty()) {
            searchPhotos()
        } else {
            _searchedPhotosLiveData.value = ViewState.Success(searchedPhotos)
        }
    }

    fun searchPhotos(query: String = DEFAULT_SEARCH_QUERY_VALUE) =
        viewModelScope.launch {
            try {
                val photos = searchPhotosUseCase.searchPhotos(query)
                searchedPhotosSource.insert(photos)
                getCachedPhotos()
            } catch (error: Exception) {
                _searchedPhotosLiveData.value =
                    ViewState.Error(ErrorMessageHelper(error).getMessageStringId())
                Timber.e(error)
            }
        }
}
