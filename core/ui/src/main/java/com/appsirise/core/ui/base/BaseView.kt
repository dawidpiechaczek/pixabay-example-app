package com.appsirise.core.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseView<LISTENER_TYPE, BINDING : ViewDataBinding>(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    @LayoutRes private val layoutId: Int
) {

    val binding: BINDING = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
    val rootView: View = binding.root

    protected val listeners = HashSet<LISTENER_TYPE>()
    protected val context: Context get() = rootView.context

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }
}
