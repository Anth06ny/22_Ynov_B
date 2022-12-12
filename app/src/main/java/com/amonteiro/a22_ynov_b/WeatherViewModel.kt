package com.amonteiro.a22_ynov_b

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amonteiro.a22_ynov_b.exokotlin.RequestUtils
import com.amonteiro.a22_ynov_b.exokotlin.WeatherBean
import kotlin.concurrent.thread


class WeatherViewModel : ViewModel() {

    val weather = MutableLiveData<WeatherBean?>()
    val errorMessage = MutableLiveData("")
    //Progressbar
    val runInProgress = MutableLiveData(false)

    fun loadData() {
        //reset des données
        //Postvalue garanti l'appel sur l''UIThread de l'observateur
        weather.postValue(null)
        errorMessage.postValue("")
        //j'indique que la tache se lance
        runInProgress.postValue(true)

        thread {
            try {
                //Appel API
                weather.postValue(RequestUtils.loadWeather("Toulouse"))
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue("Une erreur est survenue : ${e.message}")
            }
            runInProgress.postValue(false)
        }
    }




}