package com.example.grzesiek.myapplication.screens.main.listitems

import com.example.grzesiek.myapplication.UnitTest
import com.example.grzesiek.myapplication.data.models.localdatabase.DailyForecast
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherData
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import com.example.grzesiek.myapplication.utils.WeatherConsts
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.util.*

class WeatherJustNowListItemTest : UnitTest() {
    @Test
    fun `test item data`() {
        val mondayDate = Calendar.getInstance()
        mondayDate.set(2019, 3, 8)
        val weatherData = WeatherData(0, "Rzeszów", "Pada deszcz", "icon",20.0)
        val dailyForecastListItem = WeatherJustNowListItem(WeatherDataWithForecast(weatherData))
        dailyForecastListItem.icon shouldEqual "icon"
        dailyForecastListItem.location shouldEqual "Rzeszów"
        dailyForecastListItem.temp shouldEqual "20".plus(WeatherConsts.CELSIUS_DEGREE_SIGN)
    }
}