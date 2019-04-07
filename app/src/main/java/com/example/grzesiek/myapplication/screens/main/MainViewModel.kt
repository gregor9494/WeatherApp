package com.example.grzesiek.myapplication.screens.main

import androidx.lifecycle.MutableLiveData
import com.example.grzesiek.myapplication.R
import com.example.grzesiek.myapplication.core.platform.BaseViewModel
import com.example.grzesiek.myapplication.core.platform.Event
import com.example.grzesiek.myapplication.data.repository.ActualWeatherRepository
import com.example.grzesiek.myapplication.data.repository.WeatherErrorParser
import com.example.grzesiek.myapplication.utils.withDefault
import javax.inject.Inject

class MainViewModel
@Inject constructor(private val actualWeatherRepository: ActualWeatherRepository) : BaseViewModel() {

    val actualState = MutableLiveData<MainViewState>().apply { value = MainViewState.Loading() }
    val actualErrorMessage = MutableLiveData<Event<Int>>()

    fun loadActualWeather() {
        actualState.value = MainViewState.Loading()
        addSubscription(
                actualWeatherRepository.getActualCityWeather()
                        .subscribe({
                            actualState.value = MainViewState.Result(it)
                        }, { it.printStackTrace() }))
    }

    fun listenWeatherUpdateError() {
        addSubscription(
                actualWeatherRepository.updateErrors()
                        .map { WeatherErrorParser.parse(it) }
                        .subscribe({
                            if (actualState.value !is MainViewState.Result) {
                                actualState.value = MainViewState.Error(it)
                            } else {
                                actualErrorMessage.value = Event(it.messageRes)
                            }
                        }, { it.printStackTrace() })
        )
    }


}