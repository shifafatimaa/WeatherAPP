package com.example.myapp.model

import java.io.Serializable

data class WeatherItem(
    val cityName: String,
    val feelsLike: Double,
    val grndLevel: Int,
    val humidity: Int,
    val seaLevel: Int,
    val temp: Int,
    val tempMax: Int,
    val tempMin: Int,
    val icon: String,
    val description:String,
    val date : String
): java.io.Serializable
