package com.androiddevs.mvvmnewsapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

object Extension {

    fun String.toast(context: Context) {
        Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
    }

    fun String.snackbar(view: View) {
        Snackbar.make(view, this, Snackbar.LENGTH_LONG).show()
    }
}