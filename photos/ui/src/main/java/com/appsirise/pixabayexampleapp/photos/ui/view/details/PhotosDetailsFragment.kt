package com.appsirise.pixabayexampleapp.photos.ui.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.pixabayexampleapp.photos.ui.view.PhotosViewFactory
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
internal class PhotosDetailsFragment : Fragment(), PhotosDetailsView.Listener {

    @Inject
    lateinit var viewFactory: PhotosViewFactory
    private val args: PhotosDetailsFragmentArgs by navArgs()
    private var photosListView: PhotosDetailsView? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val photosListViewModel: PhotoDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        photosListView = viewFactory.searchedPhotoDetailsView(container)
        return photosListView?.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        getPhotoDetails()
    }

    private fun initObservers() {
        photosListViewModel.photoDetailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Error -> {
                    Timber.e(getString(it.errorMessage))
                }
                is ViewState.Success -> photosListView?.setPhotoDetails(it.data)
            }
        }
    }

    private fun getPhotoDetails() {
        val photoId: Long = args.photoId
        photosListViewModel.getPhotoDetails(photoId)
    }

    override fun onStart() {
        super.onStart()
        photosListView?.registerListener(this)
    }

    override fun onStop() {
        photosListView?.unregisterListener(this)
        super.onStop()
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        photosListView = null
        super.onDestroyView()
    }

    override fun onClickNavigateBack() {
        findNavController().navigateUp()
    }
}
