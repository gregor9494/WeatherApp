package com.example.grzesiek.myapplication.data.models.localdatabase

import androidx.room.Relation
import androidx.room.Transaction

data class WeatherDataWithForecast(
        var id: Long,
        var cityName: String = "",
        var state: String = "",
        var icon: String = "",
        var temperature: Double = 0.0) {
    constructor(weatherModel: WeatherData) : this(
            id = weatherModel.id,
            cityName = weatherModel.cityName,
            temperature = weatherModel.temperature,
            state = weatherModel.state,
            icon = weatherModel.icon
    )

    @Relation(entity = DailyForecast::class, parentColumn = "id", entityColumn = "weatherDataID")
    var forecastDaily: List<DailyForecast>? = null
        @Transaction get
    @Relation(entity = HourForecast::class, parentColumn = "id", entityColumn = "weatherDataID")
    var forecastHours: List<HourForecast>? = null
        @Transaction get
}
