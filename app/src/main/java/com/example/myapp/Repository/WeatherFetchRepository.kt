package com.example.myapp.Repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresExtension
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.myapp.apiCall.RetrofitHelper
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
class WeatherFetchRepository {
    private val weatherApi = RetrofitHelper.retrofit

    @OptIn(UnstableApi::class)
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun fetchWeather(cityName: String, apiKey: String): WeatherData {
        Log.d("de","This is me")
        return withContext(Dispatchers.IO) {
            try {
                Log.d("de","This is me")
                val response = weatherApi.getWeather(cityName, apiKey)
                Log.d("de","This is me")
                val weatherItems = response.list.map { data ->
                    val weather = data.weather.first()
                    Log.d("WeatherItem", "Date from API: ${data.dt_txt}")

                    WeatherItem(
                        cityName = response.city.name,
                        feelsLike = data.main.feels_like,
                        grndLevel = data.main.grnd_level,
                        humidity = data.main.humidity,
                        seaLevel = data.main.sea_level,
                        temp = data.main.temp.toInt(),
                        tempMax = data.main.temp_max.toInt(),
                        tempMin = data.main.temp_min.toInt(),
                        icon = weather.icon,
                        description = weather.description,
                        date = data.dt_txt
                    )
                }
                WeatherData(weatherItems)
            } catch (e: IOException) {
                throw e
            } catch (e: HttpException) {
                // Handle HTTP errors
                throw e
            }
        }
    }
}
