package com.amonteiro.a22_ynov_b

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

        model.runInProgress.observe(this ){
                binding.progressBar.isVisible = it
        }

        /* -------------------------------- */
        // Abonnement aux clics
        /* -------------------------------- */
        binding.btLoad.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                //On a la permission
                showWeather()
            }
            else  {
                 //Demande
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 0)
            }
        }
    }

    //Callback demande de permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            //On a la permission
            showWeather()
        }
        else  {
            //Demande
            model.errorMessage.postValue("Il faut la permission")
        }
    }


    fun showWeather(){
        //Lance l'action
        model.loadData(this)
    }




}



