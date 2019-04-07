package com.example.grzesiek.myapplication.di.koin

import com.example.grzesiek.myapplication.api.ActualWeatherService
import org.koin.dsl.module

val apiModule = module{
    single< ActualWeatherService> {ActualWeatherService(get())}
}