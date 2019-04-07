package com.example.grzesiek.myapplication.data.models.weatherapi.forecast

data class TemperatureModel(
        val day: Double,
        val min: Double,
        val max: Double,
        val night: Double,
        val eve: Double,
        val morn: Double
)