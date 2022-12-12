package com.amonteiro.a22_ynov_b

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a22_ynov_b.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //
        binding.btLoad.setOnClickListener {

            //Avant le thread je lance la progressBar
            binding.progressBar.isVisible = true
            binding.tvError.isVisible = false

            //Début d'une tache asynchrone (Permission Internet dans l'AndroidManifest)
            thread {
                //Lance l'action
                model.loadData()
                runOnUiThread {
                    refreshScreen()
                    //Je masque la progressBar
                    binding.progressBar.isVisible = false
                }
            }
        }

        refreshScreen()
    }

    fun refreshScreen() {

        /* -------------------------------- */
        // Cas qui marche
        /* -------------------------------- */
        //Je me sers de l'elvis operator pour gérer le cas null
        binding.tv.text = model.weather?.name ?: "-"
        binding.tvTemp.text = "${model.weather?.temperature?.temp ?: "-"}°"

        binding.tvDesc.text = "-"
        binding.ivTemp.setImageDrawable(null)

        //If else classique
        if (model.weather != null) {
            binding.tvWind.text = "${model.weather?.wind?.speed}"
            binding.tvMinMax.text = "(${model.weather!!.temperature.temp_min}°/${model.weather!!.temperature.temp_max}°)"

            if (!model.weather?.weather.isNullOrEmpty()) {
                binding.tvDesc.text = model.weather?.weather?.get(0)?.description
                Picasso.get().load("https://openweathermap.org/img/wn/${model.weather!!.weather[0].icon}@4x.png")
                    .placeholder(R.drawable.ic_baseline_flag_24)
                    .error(R.drawable.ic_baseline_delete_forever_24)
                    .into(binding.ivTemp)
            }
        }
        else {
            binding.tvWind.text = "-"
            binding.tvMinMax.text = "-"
        }

        /* -------------------------------- */
        // Erreur
        /* -------------------------------- */
        if (model.errorMessage.isNotBlank()) {
            binding.tvError.isVisible = true
            binding.tvError.text = "Une erreur est survenue : ${model.errorMessage}"
        }
        else {
            binding.tvError.isVisible = false
        }

    }
}



