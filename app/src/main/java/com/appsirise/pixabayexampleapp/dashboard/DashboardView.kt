package com.appsirise.pixabayexampleapp.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import com.appsirise.core.ui.base.BaseView
import com.appsirise.pixabayexampleapp.R

class DashboardView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<DashboardView.Listener>(layoutInflater, parent, R.layout.fragment_dashboard) {

    interface Listener {
        fun onClickNavigateToAuth()
        fun onClickNavigateToDesign()
    }

    private val navigateToAuth: Button = findViewById(R.id.navigate_to_auth)
    private val navigateToDesign: Button = findViewById(R.id.navigate_to_design)

    init {
        navigateToAuth.setOnClickListener { listeners.forEach { listener -> listener.onClickNavigateToAuth() } }
        navigateToDesign.setOnClickListener { listeners.forEach { listener -> listener.onClickNavigateToDesign() } }
    }
}
