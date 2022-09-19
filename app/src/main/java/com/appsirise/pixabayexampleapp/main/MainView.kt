package com.appsirise.pixabayexampleapp.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.appsirise.core.ui.base.BaseView
import com.appsirise.core.ui.extensions.findNavControllerCompat
import com.appsirise.pixabayexampleapp.R
import com.appsirise.pixabayexampleapp.databinding.ActivityMainBinding

class MainView(
    private val fragmentManager: FragmentManager,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseView<MainView.Listener, ActivityMainBinding>(
    layoutInflater,
    parent,
    R.layout.activity_main
) {

    interface Listener {
        fun onClickSearch()
        fun onClickDashboard()
    }

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
        binding.bottomNavigationBar.isVisible = isNavigationBarVisible
    }

    private fun setOnNavigationBottomBarItemSelected() {
        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
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
