package com.amonteiro.a22_ynov_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amonteiro.a22_ynov_b.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}