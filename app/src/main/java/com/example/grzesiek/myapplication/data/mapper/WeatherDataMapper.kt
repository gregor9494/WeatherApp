package com.example.grzesiek.myapplication.data.mapper

import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherData
import com.example.grzesiek.myapplication.data.models.weatherapi.singleweather.SingleWeatherModel

object WeatherDataMapper {
    @JvmStatic
    fun map(singleWeatherModel: SingleWeatherModel): WeatherData {
        return WeatherData(
                singleWeatherModel.id ?: 0,
                singleWeatherModel.name,
                singleWeatherModel.weather?.getOrNull(0)?.description?.capitalize() ?: "",
                singleWeatherModel.weather?.getOrNull(0)?.icon ?: "",
                singleWeatherModel.main?.temp ?: 0.0
        )
    }
}