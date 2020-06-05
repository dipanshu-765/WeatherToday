package com.example.android.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_daily_forecast.view.*

class DailyForecastViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val temperature = view.findViewById<TextView>(R.id.temperature)
    private val weather = view.findViewById<TextView>(R.id.weather)

    fun bind(dailyForecast: DailyForecast){
        temperature.text = dailyForecast.temp.toString()
        weather.text = dailyForecast.description
    }
}

class DailyForecastAdapter(): ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG){
    companion object{
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>(){
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}