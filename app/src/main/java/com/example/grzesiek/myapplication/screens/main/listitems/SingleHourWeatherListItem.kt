package com.example.grzesiek.myapplication.screens.main.listitems

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.example.grzesiek.myapplication.R
import com.example.grzesiek.myapplication.data.models.localdatabase.HourForecast
import com.example.grzesiek.myapplication.utils.Formatters
import com.example.grzesiek.myapplication.utils.WeatherConsts
import com.example.grzesiek.myapplication.utils.WeatherIconHelper

import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import java.util.*

class SingleHourWeatherListItem(val data: HourForecast) : AbstractFlexibleItem<SingleHourWeatherListItem.ViewHolder>() {

    private val temperatureText: String = data.temperature.toInt().toString().plus(WeatherConsts.CELSIUS_DEGREE_SIGN)
    private val hourText: String = Formatters.HourFormatter.format(Date(data.date))
    private val icon: String = data.icon

    override fun getLayoutRes(): Int {
        return R.layout.single_hour_weather_view
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<IFlexible<*>>): ViewHolder {
        return ViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<IFlexible<*>>, holder: ViewHolder, position: Int, payloads: List<Any>) {
        holder.tvTemperature.text = temperatureText
        holder.tvHour.text = hourText
        holder.ivWeatherIcon.setImageResource(WeatherIconHelper.getImageRes(icon, holder.ivWeatherIcon.context))
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as SingleHourWeatherListItem?
        return data == that!!.data
    }

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + temperatureText.hashCode()
        return result
    }

    inner class ViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {
        val tvTemperature: TextView = view.findViewById(R.id.tvTemperature)
        val tvHour: TextView = view.findViewById(R.id.tvHour)
        val ivWeatherIcon: ImageView = view.findViewById(R.id.ivWeatherIcon)
    }
}
