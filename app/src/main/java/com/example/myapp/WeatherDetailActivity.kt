package com.example.myapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import com.example.myapp.viewModels.WeatherDetailViewModel

//class WeatherDetailActivity : AppCompatActivity() {
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        setContentView(R.layout.activity_weather_detail)
//        // Retrieve the list from the Intent, defaulting to an empty list if null
//        val weatherItems: WeatherData = intent.getSerializableExtra("Weather") as WeatherData
//        // Find RecyclerView and set up Adapter
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        val adapter = WeatherAdapter(weatherItems)
//        recyclerView.adapter = adapter
//        recyclerView.visibility = View.VISIBLE
//        adapter.onItemClickListener(object : WeatherAdapter.onItemClickListener{
//
//
//            override fun onItemClick(position: Int, listWeather: WeatherData) {
//                val intent = Intent(this@WeatherDetailActivity, ItemShowActivity::class.java)
//                intent.putExtra("Weather",weatherItems.weatherlist[position])
//                startActivity(intent)
//            }
//        })
//    }
//}

class WeatherDetailActivity : AppCompatActivity() {
    private lateinit var adapter: WeatherAdapter
    private lateinit  var weatherDetailViewModel: WeatherDetailViewModel
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("City","weatherdetailactivity")
        enableEdgeToEdge()



        setContentView(R.layout.activity_weather_detail)
        Log.d("City","weatherdetailactivi")

        weatherDetailViewModel=ViewModelProvider(this).get(WeatherDetailViewModel::class.java)

        val city=intent.getStringExtra("cityName")
        Log.d("City",city.toString())
        val apiKey= "907d232eaa93fcce054f3599021123df"

        weatherDetailViewModel.getData(city!!,apiKey)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        weatherDetailViewModel.weatherSharedInfo.observe(this){weather ->
            weather?.let{
                adapter = WeatherAdapter(weather)
                recyclerView.adapter = adapter
                recyclerView.visibility = View.VISIBLE
                adapter.onItemClickListener(object : WeatherAdapter.onItemClickListener{


                    override fun onItemClick(position: Int, listWeather: WeatherData) {
                        val intent = Intent(this@WeatherDetailActivity, ItemShowActivity::class.java)
                        intent.putExtra("Weather",weather.weatherlist[position])
                        startActivity(intent)
                    }
                })
            }

        }


    }
}
