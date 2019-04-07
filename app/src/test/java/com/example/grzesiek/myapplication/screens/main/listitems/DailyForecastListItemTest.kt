package com.example.grzesiek.myapplication.screens.main.listitems

import com.example.grzesiek.myapplication.UnitTest
import com.example.grzesiek.myapplication.data.models.localdatabase.DailyForecast
import com.example.grzesiek.myapplication.utils.WeatherConsts
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DailyForecastListItemTest : UnitTest() {
    @Test
    fun `test item data`() {
        val mondayDate = Calendar.getInstance(Locale.getDefault())
        mondayDate.set(2019, 3, 8)
        val dailyForecast = DailyForecast(0, 20.0, 20.0, 20.0, "icon", mondayDate.timeInMillis)
        val dailyForecastListItem = DailyForecastListItem(dailyForecast)
        dailyForecastListItem.icon shouldEqual "icon"
        dailyForecastListItem.temperatureText shouldEqual "20".plus(WeatherConsts.CELSIUS_DEGREE_SIGN)
        val properDayOfWeekName = SimpleDateFormat("EEEE", Locale.getDefault()).format(mondayDate.time)
        dailyForecastListItem.weekDayText shouldEqual properDayOfWeekName
    }
}