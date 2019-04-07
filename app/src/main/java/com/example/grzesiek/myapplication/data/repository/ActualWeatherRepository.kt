package com.example.grzesiek.myapplication.data.repository

import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface ActualWeatherRepository {
    fun getActualCityWeather(): Flowable<WeatherDataWithForecast>
    fun updateErrors(): Observable<Throwable>
    fun anyWeatherDataExist() : Single<Boolean>
}
