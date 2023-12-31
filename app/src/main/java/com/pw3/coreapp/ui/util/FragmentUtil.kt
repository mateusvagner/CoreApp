package com.pw3.coreapp.ui.util

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.pw3.CoreApp.R

fun Fragment.showSnackbar(message: String) {
    Snackbar
        .make(requireView(), message, Snackbar.LENGTH_LONG)
        .setAnchorView(R.id.bottom_nav_view)
        .show()
}