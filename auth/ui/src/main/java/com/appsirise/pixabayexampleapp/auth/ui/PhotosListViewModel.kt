package com.appsirise.pixabayexampleapp.auth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsirise.core.ui.utils.ErrorMessageHelper
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.pixabayexampleapp.auth.ui.usecase.SearchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class PhotosListViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
) : ViewModel() {

    private val _searchPhotosLiveData = MutableLiveData<ViewState<List<SearchedPhoto>>>()
    val searchPhotosLiveData: LiveData<ViewState<List<SearchedPhoto>>> = _searchPhotosLiveData

    fun searchPhotos() {
        viewModelScope.launch {
            try {
                _searchPhotosLiveData.value =
                    ViewState.Success(searchPhotosUseCase.searchPhotos())
            } catch (error: Exception) {
                Timber.e(error)
                _searchPhotosLiveData.value =
                    ViewState.Error(ErrorMessageHelper(error).getMessageStringId())
            }
        }
    }
}
