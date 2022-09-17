package com.appsirise.pixabayexampleapp.photos.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.appsirise.core.ui.base.BaseView
import com.appsirise.pixabayexampleapp.photos.ui.R
import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto

class PhotosListView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<PhotosListView.Listener>(layoutInflater, parent, R.layout.fragment_photos_list) {

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
