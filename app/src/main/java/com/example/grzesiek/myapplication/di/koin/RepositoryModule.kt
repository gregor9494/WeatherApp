package com.example.grzesiek.myapplication.di.koin

import com.example.grzesiek.myapplication.data.repository.ActualWeatherRepository
import com.example.grzesiek.myapplication.data.repository.ActualWeatherRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ActualWeatherRepository> { ActualWeatherRepositoryImpl(get(), get(), get(), get()) }
}