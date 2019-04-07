package com.example.grzesiek.myapplication.data.models.weatherapi.weather

import com.example.grzesiek.myapplication.data.models.weatherapi.weatherinfo.*

open class WeatherModel {
    var coord: Coord? = null
    var weather: List<Weather>? = null
    var base: String? = null
    var main: Main? = null
    var visibility: Int? = null
    var wind: Wind? = null
    var clouds: Clouds? = null
    var dt: Int? = null
    var sys: Sys? = null
    var name: String? = null
    var cod: Int? = null
}