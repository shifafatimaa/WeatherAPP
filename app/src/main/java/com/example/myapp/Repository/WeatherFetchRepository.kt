package com.example.myapp.Repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.myapp.RetrofitHelper
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
class WeatherFetchRepository {
    private val weatherApi = RetrofitHelper.retrofit

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun fetchWeather(cityName: String, apiKey: String): WeatherData {
        return withContext(Dispatchers.IO) {
            try {
                val response = weatherApi.getWeather(cityName, apiKey)
                val weatherItems = response.list.map { data ->
                    val weather = data.weather.first()
                    WeatherItem(
                        cityName = response.city.name,
                        feelsLike = data.main.feels_like,
                        grndLevel = data.main.grnd_level,
                        humidity = data.main.humidity,
                        seaLevel = data.main.sea_level,
                        temp = data.main.temp,
                        tempMax = data.main.temp_max,
                        tempMin = data.main.temp_min,
                        icon = weather.icon,
                        description = weather.description
                    )
                }
                WeatherData(weatherItems)
            } catch (e: IOException) {
                // Handle network errors
                throw e
            } catch (e: HttpException) {
                // Handle HTTP errors
                throw e
            }
        }
    }
}
