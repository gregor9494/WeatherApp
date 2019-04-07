package com.example.grzesiek.myapplication.utils

import java.text.SimpleDateFormat
import java.util.*

object Formatters {
    object WeekDayFormatter : SimpleDateFormat("EEEE", Locale.getDefault())
    object HourFormatter : SimpleDateFormat("HH:mm", Locale.getDefault())
}