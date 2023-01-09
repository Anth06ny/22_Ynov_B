package com.amonteiro.a22_ynov_b.exokotlin

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


const val URL_API_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=##1&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

const val URL_API_WEATHER_LAT_LNG = "https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"
const val URL_API_POKEMON = "https://www.amonteiro.fr/api/pokemonN3"

fun main(){
    val pokemon =   RequestUtils.loadPokemon()
    println("${pokemon.name} est de type ${pokemon.type}")
}

object RequestUtils {

    val client = OkHttpClient()
    val gson = Gson()

    fun loadWeather(lat:Double, long:Double): WeatherBean {
        //contrôle
        var json =  sendGet(URL_API_WEATHER_LAT_LNG + "&lat=$lat&lon=$long")
        val data : WeatherBean = gson.fromJson(json, WeatherBean::class.java)

        //contrôle
        return data
    }

    fun loadWeather(city:String): WeatherBean {
        //contrôle
        var json =  sendGet(URL_API_WEATHER.replace("##1", city))
        val data : WeatherBean = gson.fromJson(json, WeatherBean::class.java)

        //contrôle
        return data
    }


    fun loadPokemon(): PokemonN3Bean {
        var json =  sendGet(URL_API_POKEMON)
        val data = gson.fromJson(json, PokemonN3Bean::class.java)

        return data
    }

    fun loadPokemons(): Array<PokemonN3Bean> {
        var json =  sendGet(URL_API_POKEMON)
        val data = gson.fromJson(json, Array<PokemonN3Bean>::class.java)

        return data
    }



    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
}