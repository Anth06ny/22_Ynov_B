package com.amonteiro.a22_ynov_b.exokotlin

import com.google.gson.Gson


fun String.kids() : String {

    var res = ""

    for((i, c) in this.withIndex()){
        res += if (i % 2 == 0) c.uppercase() else c.lowercase()
    }

    return res
}

fun main(){
    val data : WeatherBean? = null
    println(data.toJson())

}

fun Number.sqrt() = Math.sqrt(this.toDouble())

fun Any?.toJson() = if(this == null) "{}" else  Gson().toJson(this)