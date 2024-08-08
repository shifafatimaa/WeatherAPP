package com.example.myapp.viewModels

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.Repository.WeatherFetchRepository
import com.example.myapp.model.WeatherData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherDetailViewModel : ViewModel(){

    private val weatherInfo = MutableLiveData<WeatherData>()
    val weatherSharedInfo:LiveData<WeatherData> get() = weatherInfo
    //make object of repo here
    private val repository = WeatherFetchRepository()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getData(city:String, apiKey:String){
        GlobalScope.launch{
            val weatherData=repository.fetchWeather(city,apiKey)
            weatherInfo.postValue(weatherData)


        }
    }





}