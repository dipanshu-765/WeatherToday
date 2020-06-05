package com.example.android.fragments

import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipCode: EditText = findViewById(R.id.zipCode)
        val searchButton: Button = findViewById(R.id.search)
        val forecastList: RecyclerView = findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter = DailyForecastAdapter()
        forecastList.adapter = dailyForecastAdapter

        searchButton.setOnClickListener{
            val zipcode = zipCode.text.toString()
            if(zipcode.length!=5)
                Toast.makeText(this, "Please enter a valid Zip Code", Toast.LENGTH_SHORT).show()
            else
                {
                    Toast.makeText(this, "Searching for Weather in Zip Code $zipcode area", Toast.LENGTH_SHORT).show()
                    forecastRepository.loadForecast(zipcode = zipcode)
                }
        }

        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            //Update Live Data Adapter
            dailyForecastAdapter.submitList(forecastItems)
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }
}