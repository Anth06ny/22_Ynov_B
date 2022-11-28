package com.amonteiro.a22_ynov_b.exokotlin

fun main() {
    var weather = RequestUtils.loadWeather("Nice")

    println("Il fait ${weather.temperature.temp} ° à ${weather.name}")

}