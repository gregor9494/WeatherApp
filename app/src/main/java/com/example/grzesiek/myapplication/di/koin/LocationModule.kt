package com.example.grzesiek.myapplication.di.koin

import com.example.grzesiek.myapplication.data.location.LocationProvider
import org.koin.dsl.module

val locationModule = module {
    single<LocationProvider> { LocationProvider(get()) }
}