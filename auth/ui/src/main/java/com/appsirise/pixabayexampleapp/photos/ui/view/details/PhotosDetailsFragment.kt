package com.appsirise.pixabayexampleapp.photos.ui.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.appsirise.core.ui.extensions.subscribeTo
import com.appsirise.pixabayexampleapp.photos.ui.model.details.PhotoDetailsAction
import com.appsirise.pixabayexampleapp.photos.ui.model.details.PhotoDetailsEffect
import com.appsirise.pixabayexampleapp.photos.ui.view.PhotosViewFactory
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
internal class PhotosDetailsFragment : Fragment(), PhotosDetailsView.Listener {

    @Inject
    lateinit var viewFactory: PhotosViewFactory
    private val args: PhotosDetailsFragmentArgs by navArgs()
    private var photosListView: PhotosDetailsView? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val photosListViewModel: PhotosDetailsViewModelImpl by viewModels()

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
        initState()
        getPhotoDetails()
    }

    private fun executeEffect(effect: PhotoDetailsEffect) {
        when (effect) {
            is PhotoDetailsEffect.PhotoDetails -> photosListView?.setPhotoDetails(effect.photoDetails)
        }
    }

    private fun getPhotoDetails() {
        val photoId: Long = args.photoId
        photosListViewModel.onAction(PhotoDetailsAction.GetPhotoDetails(photoId))
            .subscribeTo(compositeDisposable)
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

    private fun initState() {
        photosListViewModel.observeEffect()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = this::executeEffect, onError = Timber::e)
            .addTo(compositeDisposable)
    }
}
