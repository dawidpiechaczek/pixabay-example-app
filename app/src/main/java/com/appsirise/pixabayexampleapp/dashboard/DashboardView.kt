package com.appsirise.pixabayexampleapp.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appsirise.core.ui.base.BaseView
import com.appsirise.pixabayexampleapp.R
import com.appsirise.pixabayexampleapp.databinding.FragmentDashboardBinding

class DashboardView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<DashboardView.Listener, FragmentDashboardBinding>(
        layoutInflater,
        parent,
        R.layout.fragment_dashboard
    ) {

    interface Listener
}
