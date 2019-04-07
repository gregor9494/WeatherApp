package com.example.grzesiek.myapplication.data.models.localdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.grzesiek.myapplication.data.models.weatherapi.weather.WeatherModel

@Entity
data class HourForecast(
        @PrimaryKey(autoGenerate = true)
        val hourForecastID: Long? = null,
        val weatherDataID: Long,
        val temperature: Double,
        val date: Long,
        val icon: String
) {
    constructor(weatherModel: WeatherModel, weatherDataID: Long) : this(
            weatherDataID = weatherDataID,
            temperature = weatherModel.main?.temp ?: 0.0,
            date = weatherModel.dt?.toLong()?.times(1000) ?: 0L,
            icon = weatherModel.weather?.getOrNull(0)?.icon ?: ""
    )
}