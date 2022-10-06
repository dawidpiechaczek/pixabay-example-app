package com.appsirise.pixabayexampleapp.photos.ui.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.appsirise.core.ui.extensions.showDialog
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.pixabayexampleapp.photos.ui.R
import com.appsirise.pixabayexampleapp.photos.ui.view.PhotosViewFactory
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
internal class PhotosListFragment : Fragment(), PhotosListView.Listener {

    @Inject
    lateinit var viewFactory: PhotosViewFactory
    private var photosListView: PhotosListView? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val photosListViewModel: PhotosListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        photosListView = viewFactory.searchedPhotosListView(container)
        return photosListView?.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        if (savedInstanceState == null) {
            initPhotosSearch()
        }
    }

    private fun initObservers() {
        photosListViewModel.searchedPhotosLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Error -> photosListView?.showError(it.errorMessage)
                is ViewState.Success -> photosListView?.bindPhotos(it.data)
            }
        }
    }

    private fun initPhotosSearch() {
        photosListViewModel.getCachedPhotos(true)
    }

    override fun onStart() {
        super.onStart()
        photosListView?.registerListener(this)
        photosListView?.registerTextListeners()
    }

    override fun onStop() {
        photosListView?.unregisterTextListeners()
        photosListView?.unregisterListener(this)
        super.onStop()
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        photosListView = null
        super.onDestroyView()
    }

    override fun onClickShowPhotoDetailsDialog(photoId: Long) {
        requireContext().showDialog(
            title = R.string.dialog_navigate_details_title,
            message = R.string.dialog_navigate_details_message,
            negativeButtonText = R.string.button_no,
            positiveButtonText = R.string.button_yes,
            positiveButtonAction = { navigateToPhotoDetails(photoId) },
            isCancelable = false
        )
    }

    private fun navigateToPhotoDetails(photoId: Long) {
        val action =
            PhotosListFragmentDirections.actionSignUpFragmentToPhotosDetailsFragment(photoId)
        findNavController().navigate(action)
    }

    override fun onSearchQueryChanged(query: String) {
        photosListViewModel.searchPhotos(query)
    }
}
