package com.example.myapp

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import com.example.myapp.viewModels.ItemShowViewModel
import com.example.myapp.viewModels.WeatherDetailViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class ItemShowActivity : AppCompatActivity() {
    private val itemShowViewModel: ItemShowViewModel by  viewModels()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item_show)

        val cityNameTextView: TextView = findViewById(R.id.cityName1)
        val feelsLikeTextView: TextView = findViewById(R.id.feelsLike1)
        val groundLevelTextView: TextView = findViewById(R.id.groundLevel1)
        val humidityTextView: TextView = findViewById(R.id.humidity1)
        val seaLevelTextView: TextView = findViewById(R.id.seaLevel1)
        val tempTextView: TextView = findViewById(R.id.temp1)
        val tempMaxTextView: TextView = findViewById(R.id.tempMax1)
        val tempMinTextView: TextView = findViewById(R.id.tempMin1)
        val imageview: ImageView = findViewById(R.id.weatherIcon1)
        val weatherDescription:TextView= findViewById(R.id.weatherDescription1)
        val date : TextView =findViewById(R.id.date1)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the back button in the toolbar
        supportActionBar?.let {
            it.title = "Back"
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.back_arrow)
        }

        itemShowViewModel.getDetails(intent)
        itemShowViewModel.itemSharedInfo.observe(this){
            weatherItem -> weatherItem?.let{

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
            date.text="${convertToCustomDateFormat(weatherItem.date)}"
            Log.d("ItemShowActivity", "Icon URL: $iconUrl")
        }
        }
    }
    fun convertToCustomDateFormat(dateString: String): String {
        // Step 1: Parse the input date string
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = inputDateFormat.parse(dateString)

        // Step 2: Format the parsed date into the desired string format
        val outputDateFormat = SimpleDateFormat("d MMMM EEEE, h:mm a", Locale.getDefault())
        return outputDateFormat.format(date!!)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
