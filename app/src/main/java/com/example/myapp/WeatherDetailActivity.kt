package com.example.myapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.apiCall.WeatherResult
import com.example.myapp.model.WeatherData
import com.example.myapp.viewModels.WeatherDetailViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherDetailActivity : AppCompatActivity() {
    private lateinit var adapter: WeatherAdapter
    private lateinit var weatherDetailViewModel: WeatherDetailViewModel
    private lateinit var   recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("City", "weatherdetailactivity")
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather_detail)
        Log.d("City", "weatherdetailactivi")

        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        weatherDetailViewModel = ViewModelProvider(this).get(WeatherDetailViewModel::class.java)

        val city = intent.getStringExtra("cityName")
        Log.d("City", city.toString())


            // Fetch data from API if not present in SharedPreferences
            weatherDetailViewModel.getData(city!!)

        weatherDetailViewModel.weatherSharedInfo.observe(this) {
            when (it) {
                is WeatherResult.Success -> {
                    it.data?.let { weatherDataList ->
                        adapterCalling(weatherDataList)
                    }
                }
                is WeatherResult.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
    private fun adapterCalling(weatherDataList:WeatherData){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the back button in the toolbar
        supportActionBar?.let {
            it.title = "Back"
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.back_arrow)
        }
        // Update RecyclerView
        adapter = WeatherAdapter(weatherDataList)
        recyclerView.adapter = adapter
        recyclerView.visibility = View.VISIBLE

        adapter.onItemClickListener(object : WeatherAdapter.onItemClickListener {
            override fun onItemClick(position: Int, listWeather: WeatherData) {
                val intent = Intent(this@WeatherDetailActivity, ItemShowActivity::class.java)
                intent.putExtra("Weather", listWeather.weatherlist[position])
                startActivity(intent)
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}


