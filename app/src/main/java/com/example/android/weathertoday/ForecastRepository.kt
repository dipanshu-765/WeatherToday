package com.example.android.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast
    fun loadForecast(zipcode: String){
        val randomValues = List(7) { Random.nextFloat().rem(100)*100 }            //Temperature Values
        val forecastItems = randomValues.map{
            val weather = when(it){
                in 85.0 .. 100.0 -> "It's Hot out there!"
                in 70.0 .. 85.0 -> "It's cloudy <3"
                else -> "Brrr... It's Cold!"
            }
            DailyForecast(it, weather)
        }
        _weeklyForecast.setValue(forecastItems)
    }
}