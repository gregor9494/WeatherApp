package com.example.grzesiek.myapplication.data.repository

import android.Manifest
import android.util.Log
import com.example.grzesiek.myapplication.AndroidTest
import com.example.grzesiek.myapplication.RxImmediateSchedulerRule
import com.example.grzesiek.myapplication.UnitTest
import com.example.grzesiek.myapplication.api.ActualWeatherService
import com.example.grzesiek.myapplication.data.localdata.LocalDataManager
import com.example.grzesiek.myapplication.data.location.LocationProvider
import com.example.grzesiek.myapplication.data.models.error.NoLocationFoundException
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import com.example.grzesiek.myapplication.system.PermissionDispatcher
import com.gun0912.tedpermission.TedPermissionResult
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import org.amshove.kluent.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


class ActualWeatherRepositoryTest : UnitTest() {
    @get:Rule
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var actualWeatherRepository: ActualWeatherRepository

    @Mock
    private lateinit var permissionDispatcher: PermissionDispatcher

    @Mock
    private lateinit var weatherService: ActualWeatherService

    @Mock
    private lateinit var localDataManager: LocalDataManager

    @Mock
    private lateinit var locationProvider: LocationProvider

    @Before
    fun setUp() {
        actualWeatherRepository = ActualWeatherRepositoryImpl(weatherService, localDataManager, permissionDispatcher, locationProvider)
    }

    @Test
    fun `test notify error when no permissions`() {
        whenever(permissionDispatcher.request(any(), any())).thenReturn(Single.error(SecurityException()))
        whenever(localDataManager.getActualWeather()).thenReturn(Flowable.just(mock(WeatherDataWithForecast::class)))

        val test = actualWeatherRepository.updateErrors().test()
        actualWeatherRepository.getActualCityWeather().subscribe()

        test.assertSubscribed()
        test.assertValue { it is SecurityException }
        test.assertValueCount(1)
        test.dispose()
    }

    @Test
    fun `test notify error when no location`() {
        whenever(permissionDispatcher.request(any(), any())).thenReturn(Single.just(TedPermissionResult(listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))))
        whenever(localDataManager.getActualWeather()).thenReturn(Flowable.just(mock(WeatherDataWithForecast::class)))
        whenever(locationProvider.requestLocation()).thenReturn(Single.fromCallable {  throw NoLocationFoundException()})

        val test = actualWeatherRepository.updateErrors().test()
        actualWeatherRepository.getActualCityWeather().subscribe()

        test.assertSubscribed()
        test.assertValue { it is NoLocationFoundException }
        test.assertValueCount(1)
        test.dispose()
    }

}