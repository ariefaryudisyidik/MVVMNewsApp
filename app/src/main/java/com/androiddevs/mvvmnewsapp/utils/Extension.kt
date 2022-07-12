package com.androiddevs.mvvmnewsapp.utils

import android.content.Context
import android.widget.Toast

object Extension {

    fun Context.toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}