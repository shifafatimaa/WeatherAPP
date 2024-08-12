//package com.example.myapp
//
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.annotation.RequiresExtension
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.myapp.apiCall.WeatherResult
//import com.example.myapp.model.WeatherData
//import com.example.myapp.viewModels.WeatherDetailViewModel
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//
//class WeatherDetailActivity : AppCompatActivity() {
//    private lateinit var adapter: WeatherAdapter
//    private lateinit  var weatherDetailViewModel: WeatherDetailViewModel
//    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("City","weatherdetailactivity")
//        enableEdgeToEdge()
//        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//
//        setContentView(R.layout.activity_weather_detail)
//        Log.d("City","weatherdetailactivi")
//
//        weatherDetailViewModel=ViewModelProvider(this).get(WeatherDetailViewModel::class.java)
//
//        val city=intent.getStringExtra("cityName")
//        Log.d("City",city.toString())
//        val apiKey= "907d232eaa93fcce054f3599021123df"
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//
//
//        val json = sharedPreferences.getString("weather_data_list", null)
//        if(json!=null) {
//            //means data exist in shared preferences
//            val gson = Gson()
//            val type = object : TypeToken<WeatherData>() {}.type
//            val weatherd: WeatherData = gson.fromJson(json, type)
//            if (weatherd == null) {
//                adapter = WeatherAdapter(weatherd)
//            }
//        }
//        else{
//            weatherDetailViewModel.getData(city!!,apiKey)
//        }
//        weatherDetailViewModel.weatherSharedInfo.observe(this){
//            when(it){
//                is WeatherResult.Success-> {
//                    it.data?.let {
//                        val editor = sharedPreferences.edit()
//
//                        // Convert the list to JSON
//                        val gson = Gson()
//                        val json = gson.toJson(it)
//
//                        // Store JSON string in SharedPreferences
//                        editor.putString("weather_data_list", json)
//                        editor.apply()
//                        adapter = WeatherAdapter(it)
//                        recyclerView.adapter = adapter
//                        recyclerView.visibility = View.VISIBLE
//                        adapter.onItemClickListener(object : WeatherAdapter.onItemClickListener {
//                            override fun onItemClick(position: Int, listWeather: WeatherData) {
//                                val intent =
//                                    Intent(this@WeatherDetailActivity, ItemShowActivity::class.java)
//                                intent.putExtra("Weather", listWeather.weatherlist[position])
//                                startActivity(intent)
//                            }
//                        })
//                        adapter.onButtonClickListener(object : WeatherAdapter.onButtonClickListener{
//                            override fun onButtonClick() {
//                              finish()
//                            }
//                        })
//                    }
//                }
//                is WeatherResult.Error -> {
//                    Toast.makeText(this,it.error, Toast.LENGTH_SHORT).show()
//                    finish()
//                    //jo pichli activity huti usko call krdeta finish()
//
//
//                }
//            }
//
//        }
//
//
//    }
//}
package com.example.myapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
        val apiKey = "907d232eaa93fcce054f3599021123df"

        val json = sharedPreferences.getString(city.toString(), null)
        if (json != null) {
            // Data exists in SharedPreferences
            val gson = Gson()
            val type = object : TypeToken<WeatherData>() {}.type
            val weatherDataList: WeatherData? = gson.fromJson(json, type)
            if (weatherDataList != null) {
                adapterCalling(weatherDataList)
            }
        } else {
            // Fetch data from API if not present in SharedPreferences
            weatherDetailViewModel.getData(city!!, apiKey)
        }

        weatherDetailViewModel.weatherSharedInfo.observe(this) {
            when (it) {
                is WeatherResult.Success -> {
                    it.data?.let { weatherDataList ->
                        val editor = sharedPreferences.edit()

                        // Convert the list to JSON
                        val gson = Gson()
                        val json = gson.toJson(weatherDataList)

                        // Store JSON string in SharedPreferences
                        editor.putString("weather_data_list", json)
                        editor.apply()
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


