package com.example.grzesiek.myapplication.data.localdata

import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherData
import com.example.grzesiek.myapplication.data.models.weatherapi.forecast.WeatherForecastList
import com.example.grzesiek.myapplication.data.models.weatherapi.weather.WeatherList

class SaveWeatherModel(var weatherData : WeatherData) {
    var weatherForecastList : WeatherForecastList? = null
    var weatherList : WeatherList? = null
}