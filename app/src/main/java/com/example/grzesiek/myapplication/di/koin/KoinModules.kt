package com.example.grzesiek.myapplication.di.koin

object KoinModules{
    val modules = listOf(
            repositoryModule,
            applicationModule,
            locationModule,
            localDataModule,
            viewModelModule,
            apiModule,
            systemModule)
}