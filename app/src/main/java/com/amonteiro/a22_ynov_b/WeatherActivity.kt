package com.amonteiro.a22_ynov_b

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a22_ynov_b.databinding.ActivityWeatherBinding
import com.amonteiro.a22_ynov_b.exokotlin.RequestUtils
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {

            //Avant le thread je lance la progressBar
            binding.progressBar.isVisible = true
            binding.tvError.isVisible = false

            //Début d'une tache asynchrone (Permission Internet dans l'AndroidManifest)
            thread {
                try {
                    //Appel API
                    val weather = RequestUtils.loadWeather("Toulouse")

                    //Bloc qui sera executé sur le Thread principale
                    runOnUiThread {
                        binding.tv.text = weather.name
                        binding.tvTemp.text = "${weather.temperature.temp}°"
                        binding.tvWind.text = "${weather.wind.speed}"
                        binding.tvMinMax.text = "(${weather.temperature.temp_min}°/${weather.temperature.temp_max}°)"

                        if (weather.weather.isNotEmpty()) {
                            binding.tvDesc.text = weather.weather[0].description
                            Picasso.get().load("https://openweathermap.org/img/wn/${weather.weather[0].icon}@4x.png")
                                .placeholder(R.drawable.ic_baseline_flag_24)
                                .error(R.drawable.ic_baseline_delete_forever_24)
                                .into(binding.ivTemp)
                        }
                        else {
                            binding.tvDesc.text = "-"
                        }

                        //Je masque la progressBar
                        binding.progressBar.isVisible = false
                    }
                }
                catch (e: Exception) {
                    e.printStackTrace()
                    runOnUiThread {
                        binding.tvError.isVisible = true
                        binding.tvError.text = "Une erreur est survenue : ${e.message}"
                        binding.progressBar.isVisible = false
                    }
                }
            }
        }


    }


}