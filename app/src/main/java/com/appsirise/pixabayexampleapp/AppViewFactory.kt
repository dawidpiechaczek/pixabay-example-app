package com.appsirise.pixabayexampleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.appsirise.pixabayexampleapp.dashboard.DashboardView
import com.appsirise.pixabayexampleapp.design.DesignView
import com.appsirise.pixabayexampleapp.main.MainView
import javax.inject.Inject
import javax.inject.Provider

class AppViewFactory @Inject constructor(
    private val layoutInflaterProvider: Provider<LayoutInflater>,
    private val fragmentManagerProvider: Provider<FragmentManager>
) {

    fun newDashboardView(parent: ViewGroup?): DashboardView =
        DashboardView(layoutInflaterProvider.get(), parent)

    fun newDesignView(parent: ViewGroup?): DesignView =
        DesignView(layoutInflaterProvider.get(), parent)

    fun newMainView(parent: ViewGroup? = null): MainView =
        MainView(fragmentManagerProvider.get(), layoutInflaterProvider.get(), parent)
}
