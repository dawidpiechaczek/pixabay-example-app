package com.appsirise.pixabayexampleapp.photos.ui.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsirise.core.ui.utils.ErrorMessageHelper
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PhotoDetailsViewModel @Inject constructor(
    private val searchedPhotosSource: SearchedPhotosSource
) : ViewModel() {

    private val _photoDetailsLiveData = MutableLiveData<ViewState<SearchedPhoto>>()
    val photoDetailsLiveData: LiveData<ViewState<SearchedPhoto>> = _photoDetailsLiveData

    fun getPhotoDetails(photoId: Long) {
        viewModelScope.launch {
            try {
                _photoDetailsLiveData.value =
                    ViewState.Success(searchedPhotosSource.getById(photoId))
            } catch (error: Exception) {
                _photoDetailsLiveData.value =
                    ViewState.Error(ErrorMessageHelper(error).getMessageStringId())
            }
        }
    }
}
