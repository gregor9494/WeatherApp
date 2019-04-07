package com.example.grzesiek.myapplication.data.models.weatherapi.forecast

import com.example.grzesiek.myapplication.data.models.weatherapi.weather.Weather

open class ForecastModel {
    var dt: Long? = null
    var temp: TemperatureModel? = null
    var pressure: Double? = null
    var humidity: Int? = null
    var weather: List<Weather>? = null
    var speed: Double? = null
    var deg: Int? = null
    var clouds: Int? = null
    var rain: Double? = null
    var snow: Double? = null

}