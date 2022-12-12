package com.amonteiro.a22_ynov_b

import androidx.lifecycle.ViewModel
import com.amonteiro.a22_ynov_b.exokotlin.RequestUtils
import com.amonteiro.a22_ynov_b.exokotlin.WeatherBean

class WeatherViewModel : ViewModel() {

    var weather : WeatherBean? = null
    var errorMessage = ""

    fun loadData() {
        //reset des données
        weather = null
        errorMessage = ""

        try {
            //Appel API
            weather = RequestUtils.loadWeather("Toulouse")
        }
        catch (e:Exception) {
            e.printStackTrace()
            errorMessage = "Une erreur est survenue : ${e.message}"
        }
    }




}