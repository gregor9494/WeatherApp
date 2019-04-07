package com.example.grzesiek.myapplication.data.localdata

import com.example.grzesiek.myapplication.data.models.localdatabase.DailyForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.HourForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherData
import com.example.grzesiek.myapplication.data.models.weatherapi.forecast.WeatherForecastList
import com.example.grzesiek.myapplication.data.models.weatherapi.weather.WeatherList
import io.reactivex.Single

class LocalDataManager(private val weatherDatabase: WeatherDatabase) {

    fun getActualWeather() = weatherDatabase.weatherDataDao().getActualSelectedWeatherData()

    fun saveActualWather(it: WeatherData): WeatherData {
        weatherDatabase.weatherDataDao().removeActualWeatherData()
        weatherDatabase.weatherDataDao().save(it)
        return it
    }

    fun saveDailyForecast(it: WeatherForecastList?, data: WeatherData) {
        weatherDatabase.weatherDataDao().removeDailyForecasts()
        it?.list?.forEach {
            weatherDatabase.weatherDataDao().save(DailyForecast(it, data.id))
        }
    }

    fun saveHoursForecast(it: WeatherList?, data: WeatherData) {
        weatherDatabase.weatherDataDao().removeHoursForecast()
        it?.list?.forEach {
            weatherDatabase.weatherDataDao().save(HourForecast(it, data.id))
        }
    }

    fun save(saveWeatherModel: SaveWeatherModel): SaveWeatherModel {
        weatherDatabase.runInTransaction {
            saveActualWather(saveWeatherModel.weatherData)
            saveDailyForecast(saveWeatherModel.weatherForecastList, saveWeatherModel.weatherData)
            saveHoursForecast(saveWeatherModel.weatherList, saveWeatherModel.weatherData)
        }
        return saveWeatherModel
    }

    fun hasAnyWeatherData(): Single<Boolean> = weatherDatabase.weatherDataDao().getWeatherDataItemsCount().map { it > 0 }

}