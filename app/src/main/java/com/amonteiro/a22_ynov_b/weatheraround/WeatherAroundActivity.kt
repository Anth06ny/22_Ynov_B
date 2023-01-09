package com.amonteiro.a22_ynov_b.weatheraround

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amonteiro.a22_ynov_b.databinding.ActivityWeatherAroundBinding
import com.amonteiro.a22_ynov_b.exokotlin.CoordBean

class WeatherAroundActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherAroundBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherAroundViewModel::class.java) }

    val adapter = WeatherListAdapter()

    var i = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //reglkage RecyclerView
        binding.rv.adapter = adapter
        //1 colonne
        binding.rv.layoutManager = GridLayoutManager(this, 1)

        binding.btAdd.setOnClickListener {
            //J'aoute un élément
            model.list.add(0, CoordBean(i++, i++))
            adapter.submitList(model.list.toList())
        }
        binding.btDelete.setOnClickListener {
            //retire le 1er élément
            model.list.removeFirstOrNull()
            adapter.submitList(model.list.toList())
        }

    }
}