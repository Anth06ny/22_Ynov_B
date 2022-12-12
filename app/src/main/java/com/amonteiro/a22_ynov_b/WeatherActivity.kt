package com.amonteiro.a22_ynov_b

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a22_ynov_b.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* -------------------------------- */
        // Observation
        /* -------------------------------- */
        //observation de la live data du ViewModel
        model.weather.observe(this) {
            //Je me sers de l'elvis operator pour gérer le cas null
            binding.tv.text = it?.name ?: "-"
            binding.tvTemp.text = "${it?.temperature?.temp ?: "-"}°"

            binding.tvDesc.text = "-"
            binding.ivTemp.setImageDrawable(null)

            //If else classique
            if (it != null) {
                binding.tvWind.text = "${it.wind.speed}"
                binding.tvMinMax.text = "(${it.temperature.temp_min}°/${it.temperature.temp_max}°)"

                if (it.weather.isNotEmpty()) {
                    binding.tvDesc.text = it.weather?.get(0)?.description
                    Picasso.get().load("https://openweathermap.org/img/wn/${it.weather[0].icon}@4x.png")
                        .placeholder(R.drawable.ic_baseline_flag_24)
                        .error(R.drawable.ic_baseline_delete_forever_24)
                        .into(binding.ivTemp)
                }
            }
            else {
                binding.tvWind.text = "-"
                binding.tvMinMax.text = "-"
            }
        }

        model.errorMessage.observe(this) {
            binding.tvError.isVisible = it.isNotBlank()
            binding.tvError.text = "Une erreur est survenue : $it"
        }

        /* -------------------------------- */
        // Abonnement aux clics
        /* -------------------------------- */
        binding.btLoad.setOnClickListener {
            //Lance l'action
            model.loadData()
        }

    }
}



