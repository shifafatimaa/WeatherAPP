package com.example.myapp.Repository.cache

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.myapp.model.WeatherData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CacheManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    fun dataStore(weatherDataList: WeatherData) {
        Log.d("shifa", "dataStore method called")
        val editor = sharedPreferences.edit()

        // Convert weatherDataList to JSON string using Gson
        val gson = Gson()
        val json = gson.toJson(weatherDataList)

        // Save JSON string to SharedPreferences
        editor.putString("weather_data", json)
        editor.apply()
        Log.d("shifa",json)
    }

    // You might also want a method to retrieve the data
    fun retrieveWeatherData(): WeatherData? {
        val gson = Gson()
        val json = sharedPreferences.getString("weather_data", null)
        val type = object : TypeToken<WeatherData>() {}.type
        return gson.fromJson(json, type)
    }
}


//    val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//    val json = sharedPreferences.getString(city.toString(), null)
//    if (json != null) {
//        // Data exists in SharedPreferences
//        val gson = Gson()
//        val type = object : TypeToken<WeatherData>() {}.type
//        val weatherDataList: WeatherData? = gson.fromJson(json, type)
