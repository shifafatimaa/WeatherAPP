package com.example.myapp.Repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresExtension
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.myapp.MyApplication
import com.example.myapp.Repository.cache.CacheManager
import com.example.myapp.apiCall.RetrofitHelper
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
class WeatherFetchRepository {
    private val weatherApi = RetrofitHelper.retrofit
    private val cacheManager: CacheManager = CacheManager(MyApplication.applicationContext1)

    @OptIn(UnstableApi::class)
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun fetchWeather(cityName: String): WeatherData {
        val data2=cacheManager.retrieveWeatherData()
        if(data2!=null){
            return data2
        }
        else {
            return withContext(Dispatchers.IO) {
                try {
                    val response = weatherApi.getWeather(cityName, MyApplication.apiKey)
                    val weatherItems = response.list.map { data ->
                        val weather = data.weather.first()
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
                            description = weather.description.substring(0, 1)
                                .uppercase() + weather.description.substring(1).lowercase(),
                            date = data.dt_txt
                        )
                    }
                    val data1= WeatherData(weatherItems)
                    storeData(data1)
                    data1
                } catch (e: IOException) {
                    throw e
                } catch (e: HttpException) {
                    // Handle HTTP errors
                    throw e
                }
            }
        }
    }

    @OptIn(UnstableApi::class)
    fun storeData(weatherDataList:WeatherData){
        Log.d("shifa", "storeData called")
        cacheManager.dataStore(weatherDataList)

    }
}
