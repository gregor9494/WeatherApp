package com.example.grzesiek.myapplication.api

import android.location.Location
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherData
import com.example.grzesiek.myapplication.data.models.weatherapi.forecast.WeatherForecastList
import com.example.grzesiek.myapplication.data.models.weatherapi.singleweather.SingleWeatherModel
import com.example.grzesiek.myapplication.data.models.weatherapi.weather.WeatherList
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActualWeatherService
@Inject constructor(retrofit: Retrofit) {
    private val weatherApi by lazy { retrofit.create(WeatherApi::class.java) }

    fun fetchWeatherDailyForecast(data: WeatherData): Single<WeatherForecastList>? {
        return weatherApi.getForecastDaily(data.id)
                .subscribeOn(Schedulers.io())
    }

    fun fetchWeatherHoursForecast(data: WeatherData): Single<WeatherList>? {
        return weatherApi.getForecast(data.id)
                .subscribeOn(Schedulers.io())
    }

    fun fetchWeatherData(it: Location): Single<SingleWeatherModel>? {
        return weatherApi.getWeatherByCoords(it.latitude, it.longitude).subscribeOn(Schedulers.io())
    }

}
