package com.example.grzesiek.myapplication.data.models.localdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherData(
        @PrimaryKey var id: Long,
        var cityName: String = "",
        var state: String = "",
        var icon: String = "",
        var temperature: Double = 0.0
)