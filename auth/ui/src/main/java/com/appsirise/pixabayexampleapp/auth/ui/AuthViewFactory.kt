package com.appsirise.pixabayexampleapp.auth.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appsirise.pixabayexampleapp.auth.ui.view.SignUpView
import javax.inject.Inject
import javax.inject.Provider

class AuthViewFactory @Inject constructor(private val layoutInflaterProvider: Provider<LayoutInflater>) {

    fun newSignUpView(parent: ViewGroup?): SignUpView =
        SignUpView(layoutInflaterProvider.get(), parent)
}
