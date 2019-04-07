package com.example.grzesiek.myapplication.utils

import android.view.View
import androidx.lifecycle.MutableLiveData

fun Boolean.toViewVisibility(): Int {
    return if (this) View.VISIBLE else View.GONE
}

fun <T> MutableLiveData<T>.withDefault(x: T): MutableLiveData<T> {
    return apply { value = x }
}