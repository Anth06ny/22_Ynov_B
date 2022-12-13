package com.amonteiro.a22_ynov_b.weatheraround

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a22_ynov_b.R
import com.amonteiro.a22_ynov_b.WeatherViewModel
import com.amonteiro.a22_ynov_b.databinding.ActivityWeatherAroundBinding
import com.amonteiro.a22_ynov_b.exokotlin.CoordBean

class WeatherAroundActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherAroundBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherAroundViewModel::class.java) }

    var i = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btAdd.setOnClickListener {
            //J'aoute un élément
            model.list.add(CoordBean(i++, i++))
            refreshScreen()

        }
        binding.btDelete.setOnClickListener {
            //retire le 1er élément
             model.list.removeFirstOrNull()
            refreshScreen()
        }

        refreshScreen()
    }

    fun refreshScreen(){
        //Je parcours ma liste pour générer une chaine
        val texte  = model.list.joinToString("\n") { "-" + it.lat + " " + it.lon }
        binding.tv.text = texte
    }
}