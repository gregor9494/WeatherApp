package com.example.grzesiek.myapplication.screens.main

import androidx.recyclerview.widget.RecyclerView
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import com.example.grzesiek.myapplication.screens.main.listitems.DailyForecastListItem
import com.example.grzesiek.myapplication.screens.main.listitems.HoursWeatherListItem
import com.example.grzesiek.myapplication.screens.main.listitems.WeatherJustNowListItem
import eu.davidea.flexibleadapter.items.IFlexible

object MainActivityListItemsCreator {
    fun createList(data: WeatherDataWithForecast): List<IFlexible<*>> {
        val list = ArrayList<IFlexible<out RecyclerView.ViewHolder>>()
        list.add(WeatherJustNowListItem(data))
        data.forecastHours?.let { list.add(HoursWeatherListItem(data)) }
        data.forecastDaily?.forEach {
            list.add(DailyForecastListItem(data = it))
        }
        return list
    }
}