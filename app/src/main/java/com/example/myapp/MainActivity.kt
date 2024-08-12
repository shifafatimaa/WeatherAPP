package com.example.myapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapp.apiCall.RetrofitHelper

class MainActivity : AppCompatActivity() {
    private val weatherApi = RetrofitHelper.retrofit

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the back button in the toolbar
        supportActionBar?.let {
            it.title = "Back"
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.back_arrow)
        }

        val button = findViewById<Button>(R.id.button)
        val cityName=findViewById<EditText>(R.id.cityname)
        button.setOnClickListener {
            Log.d("City",cityName.text.toString())
            val intent = Intent(this@MainActivity, WeatherDetailActivity::class.java)
            intent.putExtra("cityName",cityName.text.toString())
            startActivity(intent)

        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

