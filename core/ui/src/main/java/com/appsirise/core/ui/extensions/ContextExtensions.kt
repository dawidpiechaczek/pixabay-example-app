package com.appsirise.core.ui.extensions

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import com.appsirise.core.ui.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showDialog(
    @StringRes title: Int? = null,
    @StringRes message: Int? = null,
    @StringRes positiveButtonText: Int? = null,
    @StringRes negativeButtonText: Int? = null,
    @StringRes neutralButtonText: Int? = null,
    positiveButtonAction: ((dialog: DialogInterface) -> Unit)? = null,
    negativeButtonAction: ((dialog: DialogInterface) -> Unit)? = null,
    neutralButtonAction: ((dialog: DialogInterface) -> Unit)? = null,
    isCancelable: Boolean = false
) {
    val builder = MaterialAlertDialogBuilder(this)
    with(builder) {
        title?.let { setTitle(it) }
        message?.let { setMessage(it) }

        setPositiveButton(positiveButtonText ?: R.string.button_ok) { dialog, _ ->
            positiveButtonAction?.invoke(dialog)
        }

        setNegativeButton(negativeButtonText ?: R.string.button_decline) { dialog, _ ->
            negativeButtonAction?.invoke(dialog)
        }

        neutralButtonAction?.let { action ->
            setNeutralButton(neutralButtonText ?: R.string.button_cancel) { dialog, _ ->
                action.invoke(dialog)
            }
        }

        setCancelable(isCancelable)
        show()
    }
}
