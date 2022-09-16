package com.appsirise.pixabayexampleapp.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.appsirise.pixabayexampleapp.AppViewFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment(), DashboardView.Listener {

    @Inject
    lateinit var viewFactory: AppViewFactory
    private var dashboardView: DashboardView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardView = viewFactory.newDashboardView(container)
        return dashboardView?.rootView
    }

    override fun onStart() {
        super.onStart()
        dashboardView?.registerListener(this)
    }

    override fun onStop() {
        dashboardView?.unregisterListener(this)
        super.onStop()
    }

    override fun onDestroyView() {
        dashboardView = null
        super.onDestroyView()
    }

    override fun onClickNavigateToAuth() {
        val action = DashboardFragmentDirections.actionDashboardFragmentToNavGraphAuth()
        findNavController().navigate(action)
    }

    override fun onClickNavigateToDesign() {
        val action = DashboardFragmentDirections.actionDashboardFragmentToDesignFragment()
        findNavController().navigate(action)
    }
}
