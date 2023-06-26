package com.pw3.coreapp.ui.util

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.pw3.CoreApp.R
import com.pw3.CoreApp.databinding.DialogAlertImageBinding

fun showDialog(
    context: Context,
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes positiveText: Int,
    onPositiveClicked: () -> Unit
) {
    val alertDialog: AlertDialog = context.let {
        val builder = AlertDialog.Builder(it)
        builder.apply {
            setTitle(title)
            setMessage(message)

            setPositiveButton(positiveText) { _, _ ->
                onPositiveClicked()
            }

            setNegativeButton(R.string.cancel) { _, _ ->
                // User cancelled the dialog
            }
        }
        builder.create()
    }
    alertDialog.show()
}

fun showDialogCustom(
    context: Context,
    @StringRes title: Int,
    message: String,
    @StringRes positiveText: Int,
    onPositiveClicked: () -> Unit
) {
    val alertDialog: AlertDialog = context.let {
        val builder = AlertDialog.Builder(it)
        builder.apply {
            setTitle(title)
            setMessage(message)

            setPositiveButton(positiveText) { _, _ ->
                onPositiveClicked()
            }
        }
        builder.create()
    }
    alertDialog.show()
}

fun showAlertWithImage(context: Context) {
    val inflater = LayoutInflater.from(context)
    val dialogView: DialogAlertImageBinding = DialogAlertImageBinding.inflate(inflater)

    val builder = AlertDialog.Builder(context)
    builder.setView(dialogView.root)

    val alertDialog: AlertDialog = context.let {
        builder.apply {
            setView(dialogView.root)

            setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
        }
        builder.create()
    }
    alertDialog.show()
}
