package com.example.myapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import com.squareup.picasso.Picasso

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
        val imageview: ImageView = findViewById(R.id.weatherIcon)
        val weatherDescription:TextView= findViewById(R.id.weatherDescription)


        cityNameTextView.text = weatherItem.cityName
        val iconUrl = "http://openweathermap.org/img/wn/${weatherItem.icon}@4x.png"
        Picasso.get()
            .load(iconUrl)
            .into(imageview)
        weatherDescription.text=weatherItem.description
      feelsLikeTextView.text = "Feels Like: ${weatherItem.feelsLike}°C"
       groundLevelTextView.text = "Ground Level: ${weatherItem.grndLevel} hPa"
      humidityTextView.text = "Humidity: ${weatherItem.humidity}%"
        seaLevelTextView.text = "Sea Level: ${weatherItem.seaLevel} hPa"
        tempTextView.text = "Temperature: ${weatherItem.temp}°C"
        tempMaxTextView.text = "Max Temp: ${weatherItem.tempMax}°C"
        tempMinTextView.text = "Min Temp: ${weatherItem.tempMin}°C"
        Log.d("ItemShowActivity", "Icon URL: $iconUrl")




    }
}
