package com.appsirise.pixabayexampleapp.photos.ui.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.appsirise.core.ui.extensions.showDialog
import com.appsirise.core.ui.extensions.subscribeTo
import com.appsirise.pixabayexampleapp.photos.ui.R
import com.appsirise.pixabayexampleapp.photos.ui.model.list.PhotoListAction
import com.appsirise.pixabayexampleapp.photos.ui.model.list.PhotoListEffect
import com.appsirise.pixabayexampleapp.photos.ui.model.list.PhotoListState
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
internal class PhotosListFragment : Fragment(), PhotosListView.Listener {

    @Inject
    lateinit var viewFactory: PhotosViewFactory
    private var photosListView: PhotosListView? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val photosListViewModel: PhotosListViewModelImpl by activityViewModels()

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
        initState()
        initEffect()
        if (savedInstanceState == null) {
            initPhotosSearch()
        }
    }

    private fun executeState(state: PhotoListState) {
        photosListView?.bindPhotos(state.searchedPhotos)
    }

    private fun executeEffects(effect: PhotoListEffect) {
        when (effect) {
            is PhotoListEffect.Error -> photosListView?.showError(effect.messageResource)
        }
    }

    private fun initPhotosSearch() {
        photosListViewModel.onAction(PhotoListAction.GetCachedPhotos(isInitial = true))
            .subscribeTo(compositeDisposable)
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
            negativeButtonAction = { dialog -> dialog.dismiss() },
            isCancelable = false
        )
    }

    private fun navigateToPhotoDetails(photoId: Long) {
        val action =
            PhotosListFragmentDirections.actionSignUpFragmentToPhotosDetailsFragment(photoId)
        findNavController().navigate(action)
    }

    override fun onSearchQueryChanged(query: String) {
        photosListViewModel.onAction(PhotoListAction.SearchPhotos(query))
            .subscribeTo(compositeDisposable)
    }

    private fun initEffect() {
        photosListViewModel.observeEffect()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = this::executeEffects, onError = Timber::e)
            .addTo(compositeDisposable)
    }

    private fun initState() {
        photosListViewModel.observeState()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = this::executeState, onError = Timber::e)
            .addTo(compositeDisposable)
    }
}
