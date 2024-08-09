package com.example.myapp.viewModels

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem

class ItemShowViewModel :ViewModel(){

    private val itemInfo = MutableLiveData<WeatherItem>()
    val itemSharedInfo: LiveData<WeatherItem> get() = itemInfo
    fun getDetails(intent: Intent){
        itemInfo.value=intent.getSerializableExtra("Weather") as WeatherItem?
    }
}