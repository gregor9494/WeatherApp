package com.example.grzesiek.myapplication.screens.main.listitems

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.grzesiek.myapplication.R
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast

import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder

class HoursWeatherListItem(weatherDataWithForecast: WeatherDataWithForecast) : AbstractFlexibleItem<HoursWeatherListItem.ViewHolder>() {

    val itemsList = weatherDataWithForecast.forecastHours?.map { SingleHourWeatherListItem(it) }

    override fun getLayoutRes(): Int {
        return R.layout.hours_weather_view
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<IFlexible<*>>): ViewHolder {
        val viewHolder = ViewHolder(view, adapter)
        viewHolder.recyclerView.layoutManager = LinearLayoutManager(viewHolder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        return viewHolder
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<IFlexible<*>>, holder: ViewHolder, position: Int, payloads: List<Any>) {
        holder.recyclerView.adapter = FlexibleAdapter(itemsList)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HoursWeatherListItem

        if (itemsList != other.itemsList) return false

        return true
    }

    override fun hashCode(): Int {
        return itemsList?.hashCode() ?: 0
    }

    inner class ViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    }
}
