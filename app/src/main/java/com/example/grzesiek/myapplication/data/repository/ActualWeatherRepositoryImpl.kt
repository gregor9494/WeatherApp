package com.example.grzesiek.myapplication.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import com.example.grzesiek.myapplication.api.ActualWeatherService
import com.example.grzesiek.myapplication.data.localdata.LocalDataManager
import com.example.grzesiek.myapplication.data.localdata.SaveWeatherModel
import com.example.grzesiek.myapplication.data.location.LocationProvider
import com.example.grzesiek.myapplication.data.mapper.WeatherDataMapper
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import com.example.grzesiek.myapplication.system.PermissionDispatcher
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ActualWeatherRepositoryImpl
@Inject constructor(private val weatherService: ActualWeatherService,
                    private val localDataManager: LocalDataManager,
                    private val permissionDispatcher: PermissionDispatcher,
                    private val locationProvider: LocationProvider) : ActualWeatherRepository {



    private val updateErrors = PublishSubject.create<Throwable>()

    override fun getActualCityWeather(): Flowable<WeatherDataWithForecast> {
        return localDataManager.getActualWeather()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { fetchNewData() }
                .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    fun fetchNewData() {
        updateActualWeather()
                .subscribe({}, {
                    it.printStackTrace()
                    updateErrors.onNext(it)
                })
    }

    private fun updateActualWeather(): Single<SaveWeatherModel> = getAndUpdateActualWeather()

    private fun getAndUpdateActualWeather(): Single<SaveWeatherModel> {
        return permissionDispatcher.request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribeOn(Schedulers.io())
                .flatMap { locationProvider.requestLocation() }
                .flatMap { getActualWeather(it) }
                .map { WeatherDataMapper.map(it) }
                .map { SaveWeatherModel(it) }
                .flatMap { data -> getAndUpdateDailyForecast(data) }
                .flatMap { data -> getAndUpdateHoursForecast(data) }
                .map { localDataManager.save(it) }
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getActualWeather(it: Location) = weatherService.fetchWeatherData(it)

    private fun getAndUpdateHoursForecast(data: SaveWeatherModel) =
            weatherService.fetchWeatherHoursForecast(data.weatherData)?.map {
                data.weatherList = it
                return@map data
            }?.onErrorReturnItem(data)

    private fun getAndUpdateDailyForecast(data: SaveWeatherModel) =
            weatherService.fetchWeatherDailyForecast(data.weatherData)?.map {
                data.weatherForecastList = it
                return@map data
            }?.onErrorReturnItem(data)

    override fun updateErrors(): Observable<Throwable> = updateErrors

    override fun anyWeatherDataExist(): Single<Boolean> = localDataManager.hasAnyWeatherData()

}