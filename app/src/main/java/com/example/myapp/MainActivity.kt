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
class MainActivity : AppCompatActivity() {
    private val weatherApi = RetrofitHelper.retrofit

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val cityname=findViewById<EditText>(R.id.cityname)
        button.setOnClickListener {
            // Launch a coroutine to make the network request
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    // Call the API
                    val response = weatherApi.getWeather(cityname.text.toString(), "907d232eaa93fcce054f3599021123df")

                    withContext(Dispatchers.Main) {
                        // Update UI with the response data
                        Log.d("Hello", response.toString())

                        val weatherItems = response.list.map { weather ->
                            WeatherItem(
                                cityName = response.city.name,
                                feelsLike = weather.main.feels_like,
                                grndLevel = weather.main.grnd_level,
                                humidity = weather.main.humidity,
                                seaLevel = weather.main.sea_level,
                                temp = weather.main.temp,
                                tempMax = weather.main.temp_max,
                                tempMin = weather.main.temp_min
                            )
                        }
                        Log.d("Hyy",weatherItems.toString())
                        val weather=WeatherData(weatherItems)
                        val intent = Intent(this@MainActivity, WeatherDetailActivity::class.java)
                        intent.putExtra("Weather",weather)
                        startActivity(intent)
                    }
                } catch (e: IOException) {
                    // Handle network errors
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: HttpException) {
                    // Handle HTTP errors
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "HTTP error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
