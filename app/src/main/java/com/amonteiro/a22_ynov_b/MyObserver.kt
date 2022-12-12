package com.amonteiro.a22_ynov_b

import com.amonteiro.a22_ynov_b.exokotlin.WeatherBean


val  data = MyLiveData()

//Modifie
fun main() {
    data.postValue(null)
}

fun ecouteur(){
    
   data.observer = {
       
   } 
}

class MyLiveData {

    var weather : WeatherBean? = null
    
    var observer : ((WeatherBean?)->Unit) ? = null
    
    fun postValue(weatherBean: WeatherBean?) {
        weather = weatherBean
        
        observer?.invoke(weather)
        
    }
    
}