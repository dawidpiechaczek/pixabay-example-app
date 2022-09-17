package com.appsirise.pixabayexampleapp.auth.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.appsirise.core.ui.base.BaseView
import com.appsirise.pixabayexampleapp.auth.ui.R
import com.appsirise.pixabayexampleapp.auth.ui.SearchedPhoto

class SignUpView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<SignUpView.Listener>(layoutInflater, parent, R.layout.fragment_signup) {

    interface Listener {
        fun onClickNavigateToDashboard()
    }

    private val navigateToDashboard: Button = findViewById(R.id.navigate_to_dashboard)
    private val breedsAdapter: TextView = findViewById(R.id.list_items)

    init {
        navigateToDashboard.setOnClickListener { listeners.forEach { listener -> listener.onClickNavigateToDashboard() } }
    }

    fun bindPhotos(breeds: List<SearchedPhoto>) {
        breedsAdapter.text = breeds.toString()
    }

    fun showError(errorMessage: Int) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
