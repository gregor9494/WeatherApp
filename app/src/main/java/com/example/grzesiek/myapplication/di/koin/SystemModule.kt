package com.example.grzesiek.myapplication.di.koin

import com.example.grzesiek.myapplication.system.PermissionDispatcher
import org.koin.dsl.module

val systemModule = module {
    single<PermissionDispatcher> { PermissionDispatcher(get()) }
}