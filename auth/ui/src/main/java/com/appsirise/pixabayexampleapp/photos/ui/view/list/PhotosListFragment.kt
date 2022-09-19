package com.appsirise.pixabayexampleapp.photos.ui.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.appsirise.core.ui.extensions.subscribeTo
import com.appsirise.pixabayexampleapp.photos.ui.R
import com.appsirise.pixabayexampleapp.photos.ui.model.PhotoListAction
import com.appsirise.pixabayexampleapp.photos.ui.model.PhotoListEffect
import com.appsirise.pixabayexampleapp.photos.ui.model.PhotoListState
import com.appsirise.pixabayexampleapp.photos.ui.view.PhotosViewFactory
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

const val DASHBOARD_FRAGMENT_NAVIGATION_DEEPLINK =
    "android-app://com.appsirise.pixabayexampleapp/fragment_dashboard"

@AndroidEntryPoint
class PhotosListFragment : Fragment(), PhotosListView.Listener {

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
        initPhotosSearch()
    }

    private fun executeState(state: PhotoListState) {
        photosListView?.bindPhotos(state.searchedPhotos)
        photosListView?.scrollListToPosition(state.lastRecyclerPosition)
    }

    private fun executeEffects(effect: PhotoListEffect) {
        when (effect) {
            is PhotoListEffect.Error -> photosListView?.showError(effect.messageResource)
            is PhotoListEffect.NavigateToPhotoDetails -> navigateToPhotoDetails(effect.photoId)
        }
    }

    private fun initPhotosSearch() {
        photosListViewModel.onAction(PhotoListAction.GetCachedPhotos(isInitial = true))
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

    override fun onClickNavigateToDashboard() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(DASHBOARD_FRAGMENT_NAVIGATION_DEEPLINK.toUri())
            .build()
        findNavController().run {
            popBackStack(R.id.signUpFragment, true)
            navigate(request)
        }
    }

    override fun onClickSaveSelectedPositionAndGetPhotoDetails(photoId: Long, selectedPosition: Int) {
        photosListViewModel.onAction(PhotoListAction.GetPhotoDetails(photoId, selectedPosition))
            .subscribeTo(compositeDisposable)
    }

    private fun navigateToPhotoDetails(photoId: Long) {
        val action =
            PhotosListFragmentDirections.actionSignUpFragmentToPhotosDetailsFragment(photoId)
        findNavController().navigate(action)
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