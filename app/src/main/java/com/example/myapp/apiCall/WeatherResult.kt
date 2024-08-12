package com.example.myapp.apiCall

import com.example.myapp.model.WeatherData


sealed class WeatherResult<T>(val data : T?=null ,val error:String?=null){
    class Success<T>(weatherData: T) :WeatherResult<T>(data = weatherData)
    class Error<T>(errorMessage: String):WeatherResult<T>(error = errorMessage)
}
