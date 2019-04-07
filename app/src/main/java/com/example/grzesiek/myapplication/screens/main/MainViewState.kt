package com.example.grzesiek.myapplication.screens.main

import com.example.grzesiek.myapplication.data.models.error.WeatherUpdateError
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast

sealed class MainViewState {
    class Loading() : MainViewState()
    class Error(val weatherUpdateError: WeatherUpdateError) : MainViewState()
    class Result(val weatherDataWithForecast: WeatherDataWithForecast) : MainViewState()
}