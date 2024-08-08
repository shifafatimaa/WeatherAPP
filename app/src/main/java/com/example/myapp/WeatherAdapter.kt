package com.example.myapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.model.WeatherData
import com.example.myapp.model.WeatherItem
import com.squareup.picasso.Picasso

class WeatherAdapter(private val weatherItems: WeatherData) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position:Int,listWeather:WeatherData)
    }
    fun onItemClickListener(listener: onItemClickListener){
        mListener=listener
    }
     class WeatherViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
        val cityNameTextView: TextView = itemView.findViewById(R.id.cityName)
        val feelsLikeTextView: TextView = itemView.findViewById(R.id.feelsLike)
        val groundLevelTextView: TextView = itemView.findViewById(R.id.groundLevel)
        val humidityTextView: TextView = itemView.findViewById(R.id.humidity)
        val seaLevelTextView: TextView = itemView.findViewById(R.id.seaLevel)
        val tempTextView: TextView = itemView.findViewById(R.id.temp)
        val tempMaxTextView: TextView = itemView.findViewById(R.id.tempMax)
        val tempMinTextView: TextView = itemView.findViewById(R.id.tempMin)
//        val cardview: CardView=itemView.findViewById(R.id.main_card)
         val imageview:ImageView=itemView.findViewById(R.id.weatherIcon)
         val weatherdescription:TextView=itemView.findViewById(R.id.weatherDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_items, parent, false)
        return WeatherViewHolder(view)
        //WeatherViewHolder(view)-->this line says weatherviewholder ko khali view pass kya ha abhi ,yani wo layout jisma hm data pass krana chahty hein
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weatherItems.weatherlist[position]
        holder.cityNameTextView.text = item.cityName
        holder.weatherdescription.text = "Weather Description: ${item.description}"
        holder.tempMinTextView.text = "Min Temp: ${item.tempMin}°C"
        holder.feelsLikeTextView.text = "Feels Like: ${item.feelsLike}°C"
        holder.groundLevelTextView.text = "Ground Level: ${item.grndLevel} hPa"
        holder.humidityTextView.text = "Humidity: ${item.humidity}%"
        holder.seaLevelTextView.text = "Sea Level: ${item.seaLevel} hPa"
        holder.tempTextView.text = "Temperature: ${item.temp}°C"
        holder.tempMaxTextView.text = "Max Temp: ${item.tempMax}°C"
        holder.tempMinTextView.text = "Min Temp: ${item.tempMin}°C"

        holder.itemView.setOnClickListener {
            mListener.onItemClick(position,weatherItems)
        }
    }



    override fun getItemCount(): Int {
        return weatherItems.weatherlist.size
    }
}
