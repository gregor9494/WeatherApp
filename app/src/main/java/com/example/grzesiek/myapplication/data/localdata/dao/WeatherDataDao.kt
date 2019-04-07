package com.example.grzesiek.myapplication.data.localdata.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grzesiek.myapplication.data.models.localdatabase.DailyForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.HourForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherData
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface WeatherDataDao {
    @Query("SELECT * FROM WeatherData LIMIT 1")
    fun getActualSelectedWeatherData(): Flowable<WeatherDataWithForecast>

    @Query("SELECT COUNT(id) FROM WEATHERDATA")
    fun getWeatherDataItemsCount(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(weatherData: WeatherData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(dailyForecast: DailyForecast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(hourForecast: HourForecast)

    @Query("DELETE FROM WeatherData")
    fun removeActualWeatherData()

    @Query("DELETE FROM DailyForecast")
    fun removeDailyForecasts()

    @Query("DELETE FROM HourForecast")
    fun removeHoursForecast()
}