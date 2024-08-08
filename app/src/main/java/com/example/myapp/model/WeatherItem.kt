package com.example.myapp.model

import java.io.Serializable

data class WeatherItem(
    val cityName: String,
    val feelsLike: Double,
    val grndLevel: Int,
    val humidity: Int,
    val seaLevel: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
): java.io.Serializable
