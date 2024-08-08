package com.example.myapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
//class MainActivity : AppCompatActivity() {
//    private val weatherApi = RetrofitHelper.retrofit
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val button = findViewById<Button>(R.id.button)
//        val cityname=findViewById<EditText>(R.id.cityname)
//        button.setOnClickListener {
//
//            // Launch a coroutine to make the network request
//            lifecycleScope.launch(Dispatchers.IO) {
//                try {
//                    // Call the API
//                    val response = weatherApi.getWeather(cityname.text.toString(), "907d232eaa93fcce054f3599021123df")
//
//                    withContext(Dispatchers.Main) {
//                        // Update UI with the response data
//                        Log.d("Hello", response.toString())
//                        val weatherItems = response.list.map { data ->
//                            val weather = data.weather.first() // list ma sy phla object dy ga hmy
//                            WeatherItem(
//                                cityName = response.city.name,
//                                feelsLike = data.main.feels_like,
//                                grndLevel = data.main.grnd_level,
//                                humidity = data.main.humidity,
//                                seaLevel = data.main.sea_level,
//                                temp = data.main.temp,
//                                tempMax = data.main.temp_max,
//                                tempMin = data.main.temp_min,
//                                icon = weather.icon,
//                                description = weather.description
//                            )
//                        }
//                        Log.d("Hyy",weatherItems.toString())
//                        val weather=WeatherData(weatherItems)
//                        val intent = Intent(this@MainActivity, WeatherDetailActivity::class.java)
//                        intent.putExtra("cityName",weather)
//                        startActivity(intent)
//                    }
//                } catch (e: IOException) {
//                    // Handle network errors
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(applicationContext, "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
//                    }
//                } catch (e: HttpException) {
//                    // Handle HTTP errors
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(applicationContext, "HTTP error: ${e.message}", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }
//}

class MainActivity : AppCompatActivity() {
    private val weatherApi = RetrofitHelper.retrofit

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val cityName=findViewById<EditText>(R.id.cityname)
        button.setOnClickListener {
            Log.d("City",cityName.text.toString())
            val intent = Intent(this@MainActivity, WeatherDetailActivity::class.java)
            intent.putExtra("cityName",cityName.text.toString())
            startActivity(intent)

        }
    }
}

