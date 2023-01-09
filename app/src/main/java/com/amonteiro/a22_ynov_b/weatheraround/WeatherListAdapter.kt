package com.amonteiro.a22_ynov_b.weatheraround

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amonteiro.a22_ynov_b.databinding.RowWeatherBinding
import com.amonteiro.a22_ynov_b.exokotlin.CoordBean

class WeatherListAdapter : ListAdapter<CoordBean, WeatherListAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(var binding: RowWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    class Comparator : DiffUtil.ItemCallback<CoordBean>() {
        override fun areItemsTheSame(oldItem: CoordBean, newItem: CoordBean) = oldItem === newItem

        override fun areContentsTheSame(oldItem: CoordBean, newItem: CoordBean): Boolean {
            return oldItem == newItem
        }
    }

    //Creation de ligne
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(RowWeatherBinding.inflate(LayoutInflater.from(parent.context)))

    //Affichage
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.tvtemp.text = "${data.lat} ${data.lon}"
    }

}