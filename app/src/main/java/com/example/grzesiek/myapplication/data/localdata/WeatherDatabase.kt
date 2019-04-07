package com.example.grzesiek.myapplication.data.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.grzesiek.myapplication.data.localdata.dao.WeatherDataDao
import com.example.grzesiek.myapplication.data.models.localdatabase.DailyForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.HourForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherData

@Database(entities = arrayOf(WeatherData::class, DailyForecast::class, HourForecast::class), version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    companion object {
        val DATABASE_NAME = "weatherdatabase"
    }

    abstract fun weatherDataDao(): WeatherDataDao
}
