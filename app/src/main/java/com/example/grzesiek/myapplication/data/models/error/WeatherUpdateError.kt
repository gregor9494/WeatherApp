package com.example.grzesiek.myapplication.data.models.error

import com.example.grzesiek.myapplication.R

sealed class WeatherUpdateError(val canTryAgain : Boolean = true, val messageRes : Int) {
    object PermissionError : WeatherUpdateError(false, R.string.permission_error_label)
    object LocationError : WeatherUpdateError(messageRes = R.string.location_error_label)
    object NetworkError : WeatherUpdateError(messageRes = R.string.connection_error_label)
    object UnknownError : WeatherUpdateError(messageRes = R.string.connection_error_label)
}