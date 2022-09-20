package com.appsirise.core.ui.utils

import android.text.Editable
import android.text.TextWatcher

class PixabayTextWatcher(private val doOnTextChange: (String) -> Unit) : TextWatcher {

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        doOnTextChange.invoke(text.toString())
    }

    override fun afterTextChanged(editable: Editable?) {
        // do nothing
    }
}
