package com.example.myapp.viewModels

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.Repository.WeatherFetchRepository
import com.example.myapp.apiCall.WeatherResult
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson


class WeatherDetailViewModel : ViewModel(){

    private val weatherInfo = MutableLiveData<WeatherResult<WeatherData>>()
    val weatherSharedInfo:LiveData<WeatherResult<WeatherData>>get() = weatherInfo
    //make object of repo here
    private val repository = WeatherFetchRepository()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getData(city:String){
        GlobalScope.launch{
            try {
                val weatherData=repository.fetchWeather(city)

                if (weatherData != null) {
                    weatherInfo.postValue(WeatherResult.Success(weatherData))
                }
            }
            catch (e:Exception){
                weatherInfo.postValue(WeatherResult.Error(e.message.toString()))
            }
        }
    }
//    fun storeInCache(weatherDataList:WeatherData){
//        Log.d("shifa", "storeInCache called")
//        repository.storeData(weatherDataList)
//    }





}