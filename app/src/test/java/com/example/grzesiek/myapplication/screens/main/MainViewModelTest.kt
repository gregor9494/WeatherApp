package com.example.grzesiek.myapplication.screens.main

import com.example.grzesiek.myapplication.AndroidTest
import com.example.grzesiek.myapplication.data.models.error.NoLocationFoundException
import com.example.grzesiek.myapplication.data.models.error.WeatherUpdateError
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherData
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import com.example.grzesiek.myapplication.data.repository.ActualWeatherRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.mock
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.HttpException
import java.util.concurrent.TimeUnit
import kotlin.test.assertTrue


class MainViewModelTest : AndroidTest() {

    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var actualWeatherRepository: ActualWeatherRepository

    @Before
    fun setUp() {
        whenever(actualWeatherRepository.updateErrors()).thenReturn(Observable.empty())
        mainViewModel = MainViewModel(actualWeatherRepository)
    }

    @Test
    fun `test load view model has correct state after load `() {
        whenever(actualWeatherRepository.getActualCityWeather()).thenReturn(Flowable.just(WeatherDataWithForecast(WeatherData(id = 1))))
        mainViewModel.loadActualWeather()
        assertTrue { mainViewModel.actualState.value is MainViewState.Result }
        (mainViewModel.actualState.value as MainViewState.Result).weatherDataWithForecast.id shouldEqualTo 1
    }

    @Test
    fun `test loading weather error should invoke show error`() {
        whenever(actualWeatherRepository.updateErrors()).thenReturn(Observable.just(Exception()))
        mainViewModel = MainViewModel(actualWeatherRepository)
        mainViewModel.listenWeatherUpdateError()
        mainViewModel.actualState.value shouldBeInstanceOf MainViewState.Error::class.java
    }

    @Test
    fun `test loading state until data loaded`() {
        val testScheduler = TestScheduler()
        val response = Flowable.just(WeatherDataWithForecast(WeatherData(id = 1))).subscribeOn(testScheduler).observeOn(testScheduler)
        whenever(actualWeatherRepository.getActualCityWeather()).thenReturn(response)
        mainViewModel.loadActualWeather()
        mainViewModel.actualState.value shouldBeInstanceOf MainViewState.Loading::class.java
        testScheduler.advanceTimeBy(1L, TimeUnit.SECONDS)
        mainViewModel.actualState.value shouldBeInstanceOf MainViewState.Result::class.java
    }

    @Test
    fun `test error type for unknown exception`() {
        whenever(actualWeatherRepository.updateErrors()).thenReturn(Observable.just(Exception()))
        mainViewModel = MainViewModel(actualWeatherRepository)
        mainViewModel.listenWeatherUpdateError()
        (mainViewModel.actualState.value as MainViewState.Error).weatherUpdateError shouldBeInstanceOf WeatherUpdateError.UnknownError::class.java
    }

    @Test
    fun `test error type for network exception`() {
        whenever(actualWeatherRepository.updateErrors()).thenReturn(Observable.just(mock(HttpException::class)))
        mainViewModel = MainViewModel(actualWeatherRepository)
        mainViewModel.listenWeatherUpdateError()
        (mainViewModel.actualState.value as MainViewState.Error).weatherUpdateError shouldBeInstanceOf WeatherUpdateError.NetworkError::class.java
    }

    @Test
    fun `test error type for permission exception`() {
        whenever(actualWeatherRepository.updateErrors()).thenReturn(Observable.just(SecurityException()))
        mainViewModel = MainViewModel(actualWeatherRepository)
        mainViewModel.listenWeatherUpdateError()
        (mainViewModel.actualState.value as MainViewState.Error).weatherUpdateError shouldBeInstanceOf WeatherUpdateError.PermissionError::class.java
    }


    @Test
    fun `test error type for location exception`() {
        whenever(actualWeatherRepository.updateErrors()).thenReturn(Observable.just(NoLocationFoundException()))
        mainViewModel = MainViewModel(actualWeatherRepository)
        mainViewModel.listenWeatherUpdateError()
        (mainViewModel.actualState.value as MainViewState.Error).weatherUpdateError shouldBeInstanceOf WeatherUpdateError.LocationError::class.java
    }
}