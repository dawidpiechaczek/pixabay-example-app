package com.appsirise.pixabayexampleapp.design

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appsirise.core.ui.base.BaseView
import com.appsirise.pixabayexampleapp.R
import com.appsirise.pixabayexampleapp.databinding.FragmentDesignBinding

class DesignView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseView<DesignView.Listener, FragmentDesignBinding>(layoutInflater, parent, R.layout.fragment_design) {

    interface Listener
}
