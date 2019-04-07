package com.example.grzesiek.myapplication.screens.main

import com.example.grzesiek.myapplication.AndroidTest
import com.example.grzesiek.myapplication.UnitTest
import com.example.grzesiek.myapplication.data.models.localdatabase.DailyForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.HourForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import com.example.grzesiek.myapplication.screens.main.MainActivityListItemsCreator
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class MainActivityListItemsCreatorTest : UnitTest() {
    @Test
    fun `test list size when weather data is full`() {
        val data = WeatherDataWithForecast(1, "test", "test", "test", 0.0)
        data.forecastHours = MutableList(10) { HourForecast(0L, 0L, 0.0, 1, "test") }
        data.forecastDaily = MutableList(5) { DailyForecast(1L, 0.0, 0.0, 0.0, "test", 1) }
        val list = MainActivityListItemsCreator.createList(data)
        list.size shouldEqualTo (1 + 1 + 5) // Because forecast hours is one element in this list
    }

    @Test
    fun `test list size when no hours forecast`() {
        val data = WeatherDataWithForecast(1, "test", "test", "test", 0.0)
        data.forecastDaily = MutableList(5) { DailyForecast(1L, 0.0, 0.0, 0.0, "test", 1) }
        val list = MainActivityListItemsCreator.createList(data)
        list.size shouldEqualTo (1 + 5) // Because forecast hours is one element in this list
    }

    @Test
    fun `test list size when no daily forecast`() {
        val data = WeatherDataWithForecast(1, "test", "test", "test", 0.0)
        data.forecastHours = MutableList(10) { HourForecast(0L, 0L, 0.0, 1, "test") }
        val list = MainActivityListItemsCreator.createList(data)
        list.size shouldEqualTo (1 + 1) // Because forecast hours is one element in this list
    }

    @Test
    fun `test list size when only actual weather state`() {
        val data = WeatherDataWithForecast(1, "test", "test", "test", 0.0)
        val list = MainActivityListItemsCreator.createList(data)
        list.size shouldEqualTo (1) // Because forecast hours is one element in this list
    }
}