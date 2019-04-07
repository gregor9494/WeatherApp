package com.example.grzesiek.myapplication.di.koin

import androidx.room.Room
import com.example.grzesiek.myapplication.data.localdata.LocalDataManager
import com.example.grzesiek.myapplication.data.localdata.WeatherDatabase
import org.koin.dsl.module

val localDataModule = module {
    single<WeatherDatabase> {
        Room.databaseBuilder(get(), WeatherDatabase::class.java, WeatherDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration().build()
    }

    single<LocalDataManager> { LocalDataManager(get()) }
}