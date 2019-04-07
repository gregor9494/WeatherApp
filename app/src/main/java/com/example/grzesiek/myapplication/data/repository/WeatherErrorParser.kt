package com.example.grzesiek.myapplication.data.repository

import com.example.grzesiek.myapplication.data.models.error.NoLocationFoundException
import com.example.grzesiek.myapplication.data.models.error.WeatherUpdateError
import retrofit2.HttpException

object WeatherErrorParser {
    fun parse(error: Throwable): WeatherUpdateError {
        return when (error) {
            is HttpException -> WeatherUpdateError.NetworkError
            is SecurityException -> WeatherUpdateError.PermissionError
            is NoLocationFoundException -> WeatherUpdateError.LocationError
            else -> WeatherUpdateError.UnknownError
        }
    }
}