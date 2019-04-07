package com.example.grzesiek.myapplication.data.models.localdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.grzesiek.myapplication.data.models.weatherapi.forecast.ForecastModel

@Entity
data class DailyForecast(
        val weatherDataID: Long,
        val temperature: Double,
        val temperatureMax: Double,
        val temperatureMin: Double,
        val icon: String,
        val time: Long
) {

    @PrimaryKey(autoGenerate = true)
    var dailyForecastID: Long? = null

    constructor(forecastModel: ForecastModel, weatherDataID: Long) : this(
            weatherDataID = weatherDataID,
            temperature = forecastModel.temp?.day ?: 0.0,
            temperatureMax = forecastModel.temp?.max ?: 0.0,
            temperatureMin = forecastModel.temp?.min ?: 0.0,
            icon = forecastModel.weather?.getOrNull(0)?.icon ?: "",
            time = forecastModel.dt?.times(1000) ?: 0
    )
}