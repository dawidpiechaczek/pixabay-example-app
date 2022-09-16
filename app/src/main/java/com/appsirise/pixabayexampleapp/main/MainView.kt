package com.appsirise.pixabayexampleapp.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.appsirise.core.ui.base.BaseView
import com.appsirise.core.ui.extensions.findNavControllerCompat
import com.appsirise.pixabayexampleapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainView(
    private val fragmentManager: FragmentManager,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseView<MainView.Listener>(layoutInflater, parent, R.layout.activity_main) {

    interface Listener {
        fun onClickSearch()
        fun onClickDashboard()
    }

    private val bottomNavigationBar: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

    init {
        setNavigationListener()
        setOnNavigationBottomBarItemSelected()
    }

    private fun setNavigationListener() {
        fragmentManager.findNavControllerCompat(R.id.nav_controller_main)
            .addOnDestinationChangedListener { _, destination, _ ->
                val isNavigationBarVisible = when (destination.id) {
                    R.id.dashboardFragment, R.id.designFragment -> true
                    R.id.signUpFragment -> false
                    else -> false
                }
                changeBottomNavigationBarVisibility(isNavigationBarVisible)
            }
    }

    private fun changeBottomNavigationBarVisibility(isNavigationBarVisible: Boolean) {
        bottomNavigationBar.isVisible = isNavigationBarVisible
    }

    private fun setOnNavigationBottomBarItemSelected() {
        bottomNavigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_dashboard -> {
                    listeners.forEach { listener -> listener.onClickDashboard() }
                    true
                }
                R.id.menu_search -> {
                    listeners.forEach { listener -> listener.onClickSearch() }
                    true
                }
                else -> false
            }
        }
    }
}