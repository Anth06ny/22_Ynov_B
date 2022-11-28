package com.amonteiro.a22_ynov_b

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a22_ynov_b.databinding.ActivityWeatherBinding
import com.amonteiro.a22_ynov_b.exokotlin.RequestUtils
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

            //Début d'une tache asynchrone (Permission Internet dans l'AndroidManifest)
            thread {
                //Appel API
                val weather = RequestUtils.loadWeather("Toulouse")

                //Bloc qui sera executé sur le Thread principale
                runOnUiThread {
                    binding.tvData.text = "Il fait ${weather.temperature.temp}° à ${weather.name}"
                    //Je masque la progressBar
                    binding.progressBar.isVisible = false
                }
            }

        }


    }


}