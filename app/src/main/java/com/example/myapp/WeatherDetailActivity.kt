package com.example.myapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem

class WeatherDetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_weather_detail)
        // Retrieve the list from the Intent, defaulting to an empty list if null
        val weatherItems: WeatherData = intent.getSerializableExtra("Weather") as WeatherData
        // Find RecyclerView and set up Adapter
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = WeatherAdapter(weatherItems)
        recyclerView.adapter = adapter
        recyclerView.visibility = View.VISIBLE
        adapter.onItemClickListener(object : WeatherAdapter.onItemClickListener{


            override fun onItemClick(position: Int, listWeather: WeatherData) {
                val intent = Intent(this@WeatherDetailActivity, ItemShowActivity::class.java)
                intent.putExtra("Weather",weatherItems.weatherlist[position])
                startActivity(intent)
            }
        })
    }
}
