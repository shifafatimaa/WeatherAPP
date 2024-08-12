package com.example.myapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.model.WeatherData
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherAdapter(private val weatherItems: WeatherData) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int, listWeather: WeatherData)
    }

    fun onItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tempTextView: TextView = itemView.findViewById(R.id.temp)
        val tempMaxTextView: TextView = itemView.findViewById(R.id.tempMax)

        val imageview: ImageView = itemView.findViewById(R.id.weatherIcon)
        val weatherDescription: TextView = itemView.findViewById(R.id.weatherDescription)
        val weatherDate: TextView = itemView.findViewById(R.id.date)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_items, parent, false)
        return WeatherViewHolder(view)
        //WeatherViewHolder(view)-->this line says weatherviewholder ko khali view pass kya ha abhi ,yani wo layout jisma hm data pass krana chahty hein
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weatherItems.weatherlist[position]
        holder.weatherDescription.text = "${item.description}"
        holder.tempTextView.text = "${item.temp}°C"
        holder.tempMaxTextView.text = "/${item.tempMax}°C"
        holder.weatherDate.text = "${convertToCustomDateFormat(item.date)}"
        Log.d("Date", item.date)


        holder.itemView.setOnClickListener {
            mListener.onItemClick(position, weatherItems)
        }
        val iconUrl = "http://openweathermap.org/img/wn/${item.icon}@4x.png"
        Picasso.get()
            .load(iconUrl)
            .into(holder.imageview)
    }
    override fun getItemCount(): Int {
        return weatherItems.weatherlist.size
    }
    fun convertToCustomDateFormat(dateString: String): String {
        // Step 1: Parse the input date string
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = inputDateFormat.parse(dateString)

        // Step 2: Format the parsed date into the desired string format
        val outputDateFormat = SimpleDateFormat("d MMMM EEEE, h:mm a", Locale.getDefault())
        return outputDateFormat.format(date!!)
    }


}


//package com.example.myapp
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.myapp.model.WeatherData
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//class WeatherAdapter(private val weatherItems: WeatherData) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
//
//    private lateinit var mListener: onItemClickListener
//    private lateinit var buttonListener: onButtonClickListener
//
//    interface onItemClickListener {
//        fun onItemClick(position: Int, listWeather: WeatherData)
//    }
//
//    fun onItemClickListener(listener: onItemClickListener) {
//        mListener = listener
//    }
//
//    //setting interface for button
//    interface onButtonClickListener {
//        fun onButtonClick()
//    }
//
//    fun onButtonClickListener(listener2: onButtonClickListener) {
//        buttonListener = listener2
//    }
//
//    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val cityNameTextView: TextView = itemView.findViewById(R.id.cityName)
//        val feelsLikeTextView: TextView = itemView.findViewById(R.id.feelsLike)
//        val groundLevelTextView: TextView = itemView.findViewById(R.id.groundLevel)
//        val humidityTextView: TextView = itemView.findViewById(R.id.humidity)
//        val seaLevelTextView: TextView = itemView.findViewById(R.id.seaLevel)
//        val tempTextView: TextView = itemView.findViewById(R.id.temp)
//        val tempMaxTextView: TextView = itemView.findViewById(R.id.tempMax)
//        val tempMinTextView: TextView = itemView.findViewById(R.id.tempMin)
//        val imageview: ImageView = itemView.findViewById(R.id.weatherIcon)
//        val weatherDescription: TextView = itemView.findViewById(R.id.weatherDescription)
//        val button: Button = itemView.findViewById(R.id.button2)
//        val weatherDate: TextView = itemView.findViewById(R.id.date)
//
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.weather_items, parent, false)
//        return WeatherViewHolder(view)
//        //WeatherViewHolder(view)-->this line says weatherviewholder ko khali view pass kya ha abhi ,yani wo layout jisma hm data pass krana chahty hein
//    }
//
//    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
//        val item = weatherItems.weatherlist[position]
//        holder.cityNameTextView.text = item.cityName
//        holder.weatherDescription.text = "Weather Description: ${item.description}"
//        holder.tempMinTextView.text = "Min Temp: ${item.tempMin}°C"
//        holder.feelsLikeTextView.text = "Feels Like: ${item.feelsLike}°C"
//        holder.groundLevelTextView.text = "Ground Level: ${item.grndLevel} hPa"
//        holder.humidityTextView.text = "Humidity: ${item.humidity}%"
//        holder.seaLevelTextView.text = "Sea Level: ${item.seaLevel} hPa"
//        holder.tempTextView.text = "Temperature: ${item.temp}°C"
//        holder.tempMaxTextView.text = "Max Temp: ${item.tempMax}°C"
//        holder.tempMinTextView.text = "Min Temp: ${item.tempMin}°C"
////        holder.weatherDate.text="Date: ${convertToCustomDateFormat(item.date)}"
////        Log.d("Date",item.date)
//        holder.weatherDate.text = "Date: ${convertToCustomDateFormat(item.date)}"
//        Log.d("Date", item.date)
//
//
//        holder.itemView.setOnClickListener {
//            mListener.onItemClick(position, weatherItems)
//        }
//        holder.button.setOnClickListener {
//            buttonListener.onButtonClick()
//        }
//
//    }
//    override fun getItemCount(): Int {
//        return weatherItems.weatherlist.size
//    }
//    fun convertToCustomDateFormat(dateString: String): String {
//        // Step 1: Parse the input date string
//        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//        val date = inputDateFormat.parse(dateString)
//
//        // Step 2: Format the parsed date into the desired string format
//        val outputDateFormat = SimpleDateFormat("d MMMM EEEE, h:mm a", Locale.getDefault())
//        return outputDateFormat.format(date!!)
//    }
//
//
//}
//
