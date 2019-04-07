package com.example.grzesiek.myapplication.data.models.weatherapi.singleweather

import com.example.grzesiek.myapplication.data.models.weatherapi.weather.Weather
import com.example.grzesiek.myapplication.data.models.weatherapi.weatherinfo.*

open class SingleWeatherModel {
    var id: Long? = null
    var coord: Coord? = null
    var weather: List<Weather>? = null
    var base: String? = null
    var main: Main? = null
    var visibility: Int? = null
    var wind: Wind? = null
    var clouds: Clouds? = null
    var dt: Int? = null
    var sys: Sys? = null
    var name: String = ""
    var cod: Int? = 200
    var fromLocation: Boolean = false
}