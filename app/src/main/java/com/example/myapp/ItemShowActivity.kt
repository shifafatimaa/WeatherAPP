package com.example.myapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem

class ItemShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.weather_items)
        val weatherItem: WeatherItem = intent.getSerializableExtra("Weather") as WeatherItem
        val cityNameTextView: TextView = findViewById(R.id.cityName)
        val feelsLikeTextView: TextView = findViewById(R.id.feelsLike)
        val groundLevelTextView: TextView = findViewById(R.id.groundLevel)
        val humidityTextView: TextView = findViewById(R.id.humidity)
        val seaLevelTextView: TextView = findViewById(R.id.seaLevel)
        val tempTextView: TextView = findViewById(R.id.temp)
        val tempMaxTextView: TextView = findViewById(R.id.tempMax)
        val tempMinTextView: TextView = findViewById(R.id.tempMin)

       cityNameTextView.text = weatherItem.cityName
      feelsLikeTextView.text = "Feels Like: ${weatherItem.feelsLike}°C"
       groundLevelTextView.text = "Ground Level: ${weatherItem.grndLevel} hPa"
      humidityTextView.text = "Humidity: ${weatherItem.humidity}%"
        seaLevelTextView.text = "Sea Level: ${weatherItem.seaLevel} hPa"
        tempTextView.text = "Temperature: ${weatherItem.temp}°C"
        tempMaxTextView.text = "Max Temp: ${weatherItem.tempMax}°C"
        tempMinTextView.text = "Min Temp: ${weatherItem.tempMin}°C"


    }
}
