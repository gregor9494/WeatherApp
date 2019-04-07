package com.example.grzesiek.myapplication.data.models.weatherapi.forecast

open class WeatherForecastList {
    var cod: Int = 0
    var id: Int = 0
    var list: List<ForecastModel>? = null
}