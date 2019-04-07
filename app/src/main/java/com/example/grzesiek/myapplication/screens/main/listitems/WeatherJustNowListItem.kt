package com.example.grzesiek.myapplication.screens.main.listitems

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.grzesiek.myapplication.R
import com.example.grzesiek.myapplication.data.models.localdatabase.WeatherDataWithForecast
import com.example.grzesiek.myapplication.utils.WeatherConsts
import com.example.grzesiek.myapplication.utils.WeatherIconHelper
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder


class WeatherJustNowListItem(weatherDataWithForecast: WeatherDataWithForecast) : AbstractFlexibleItem<WeatherJustNowListItem.ViewHolder>() {

    val temp = weatherDataWithForecast.temperature.toInt().toString().plus(WeatherConsts.CELSIUS_DEGREE_SIGN)
    val icon = weatherDataWithForecast.icon
    val location = weatherDataWithForecast.cityName

    override fun getLayoutRes(): Int {
        return R.layout.weather_just_now_view
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<IFlexible<*>>): ViewHolder {
        return ViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<IFlexible<*>>, holder: ViewHolder, position: Int, payloads: List<Any>) {
        holder.tvTemperature.text = temp
        holder.ivWeatherIcon.setImageResource(WeatherIconHelper.getImageRes(icon, holder.ivWeatherIcon.context))
        holder.tvLocation.text = location
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherJustNowListItem

        if (temp != other.temp) return false
        if (icon != other.icon) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = temp.hashCode()
        result = 31 * result + icon.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }


    inner class ViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {
        val tvTemperature: TextView = view.findViewById(R.id.tvTemperature)
        val ivWeatherIcon: ImageView = view.findViewById(R.id.ivWeatherIcon)
        val tvLocation: TextView = view.findViewById(R.id.tvLocation)
    }
}
