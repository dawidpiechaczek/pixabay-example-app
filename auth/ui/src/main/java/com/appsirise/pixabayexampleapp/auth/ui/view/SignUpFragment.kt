package com.appsirise.pixabayexampleapp.auth.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.pixabayexampleapp.auth.ui.AuthViewFactory
import com.appsirise.pixabayexampleapp.auth.ui.PhotosListViewModel
import com.appsirise.pixabayexampleapp.auth.ui.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val DASHBOARD_FRAGMENT_NAVIGATION_DEEPLINK =
    "android-app://com.appsirise.pixabayexampleapp/fragment_dashboard"

@AndroidEntryPoint
class SignUpFragment : Fragment(), SignUpView.Listener {

    @Inject
    lateinit var viewFactory: AuthViewFactory
    private var signUpView: SignUpView? = null

    private val authViewModel: PhotosListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpView = viewFactory.newSignUpView(container)
        return signUpView?.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        authViewModel.searchPhotos()
    }

    override fun onStart() {
        super.onStart()
        signUpView?.registerListener(this)
    }

    override fun onStop() {
        signUpView?.unregisterListener(this)
        super.onStop()
    }

    override fun onDestroyView() {
        signUpView = null
        super.onDestroyView()
    }

    private fun initObservers() {
        authViewModel.searchPhotosLiveData.observe(viewLifecycleOwner) { dogBreedsState ->
            when (dogBreedsState) {
                is ViewState.Error -> signUpView?.showError(dogBreedsState.errorMessage)
                is ViewState.Success -> signUpView?.bindPhotos(dogBreedsState.data)
            }
        }
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
}
