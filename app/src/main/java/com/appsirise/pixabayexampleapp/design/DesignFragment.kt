package com.appsirise.pixabayexampleapp.design

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appsirise.pixabayexampleapp.AppViewFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DesignFragment : Fragment(), DesignView.Listener {

    @Inject
    lateinit var viewFactory: AppViewFactory
    private var designView: DesignView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        designView = viewFactory.newDesignView(container)
        return designView?.rootView
    }

    override fun onDestroyView() {
        designView = null
        super.onDestroyView()
    }
}
