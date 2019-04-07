package com.example.grzesiek.myapplication.utils

import android.content.Context

object WeatherIconHelper {
    fun getImageRes(imageName: String, context: Context): Int {
        return context.resources.getIdentifier("i$imageName", "drawable", context.packageName)
    }
}