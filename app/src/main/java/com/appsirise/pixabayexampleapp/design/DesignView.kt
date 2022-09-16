package com.appsirise.pixabayexampleapp.design

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appsirise.core.ui.base.BaseView
import com.appsirise.pixabayexampleapp.R

class DesignView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<DesignView.Listener>(layoutInflater, parent, R.layout.fragment_design) {

    interface Listener
}
