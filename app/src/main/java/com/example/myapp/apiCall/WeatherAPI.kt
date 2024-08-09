package com.example.myapp.apiCall

import com.example.myapp.model.Example
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("forecast")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Example
}
