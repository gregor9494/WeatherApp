package com.example.grzesiek.myapplication.screens.main.listitems

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.grzesiek.myapplication.R
import com.example.grzesiek.myapplication.data.models.localdatabase.DailyForecast
import com.example.grzesiek.myapplication.utils.Formatters
import com.example.grzesiek.myapplication.utils.WeatherConsts
import com.example.grzesiek.myapplication.utils.WeatherIconHelper
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import java.util.*

class DailyForecastListItem(val data: DailyForecast) : AbstractFlexibleItem<DailyForecastListItem.ViewHolder>() {

    val temperatureText: String = data.temperature.toInt().toString().plus(WeatherConsts.CELSIUS_DEGREE_SIGN)
    val weekDayText: String = Formatters.WeekDayFormatter.format(Date(data.time))
    val icon: String = data.icon

    override fun getLayoutRes(): Int {
        return R.layout.daily_forecast_list_item_view
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<IFlexible<*>>): ViewHolder {
        return ViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<IFlexible<*>>, holder: ViewHolder, position: Int, payloads: List<Any>) {
        holder.tvTemperature.text = temperatureText
        holder.tvWeekDay.text = weekDayText
        holder.ivWeatherIcon.setImageResource(WeatherIconHelper.getImageRes(icon, holder.ivWeatherIcon.context))
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as DailyForecastListItem?
        return data == that!!.data
    }

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + temperatureText.hashCode()
        result = 31 * result + weekDayText.hashCode()
        return result
    }

    inner class ViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {
        val tvTemperature: TextView = view.findViewById(R.id.tvTemperature)
        val tvWeekDay: TextView = view.findViewById(R.id.tvWeekDay)
        val ivWeatherIcon: ImageView = view.findViewById(R.id.ivWeatherIcon)
    }


}
