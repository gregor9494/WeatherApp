package com.example.grzesiek.myapplication.api

import com.example.grzesiek.myapplication.data.models.weatherapi.forecast.WeatherForecastList
import com.example.grzesiek.myapplication.data.models.weatherapi.singleweather.SingleWeatherModel
import com.example.grzesiek.myapplication.data.models.weatherapi.weather.WeatherList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    fun getForecast(@Query("id") cityID: Long): Single<WeatherList>

    @GET("forecast/daily")
    fun getForecastDaily(@Query("id") cityID: Long): Single<WeatherForecastList>

    @GET("weather")
    fun getWeatherByCoords(@Query("lat") lat: Double, @Query("lon") lon: Double): Single<SingleWeatherModel>
}