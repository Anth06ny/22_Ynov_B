package com.amonteiro.a22_ynov_b.exokotlin

import com.google.gson.annotations.SerializedName
import java.util.*


fun main() {
    val randomName = RandomName()
    randomName.add("Toto")
    repeat(10) {
        println(randomName.next() + " ")
    }

}

/* -------------------------------- */
// Exo Beans
/* -------------------------------- */

class  RandomName {
    private val list = arrayListOf("Bobby", "Bob", "Tobby")

    fun add(name : String?) = if(!name.isNullOrBlank() && name !in list) list.add(name) else false

    fun next() = list.random()

    private var lastValue = ""

    fun nextDiff(): String {
        var value = next()
        while(lastValue == value) {
            value = next()
        }
        lastValue = value
        return value
    }

    fun nextDiffV2() = list.filter { it != lastValue }.random().also {
        lastValue = it
    }


    fun next2() = Pair(nextDiff(), nextDiff())

}

class PlaneBean(name: String){
    var id = name.hashCode()
        private set

    var name = name
        set(value) {
            field = value
            id = value.hashCode()
        }
}

data class UserBean(val name: String, var note : Int = 0){
    val id = name.hashCode()
}

class PrintRandomIntBean(val max : Int ){
    val random : Random = Random()

    init {
        println("init")
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println("constructor()")
        println(random.nextInt(max))
    }

}

class StudentBean(val name : String){
    var note = 0
}


data class CarBean(var marque :String = "", var model:String? = null, var couleur:String? ="")   {
    var year = 2000
}

class CarBean2 {
    var marque  = ""
    var model:String? = null
    var couleur:String? =""

    fun print() = "coucou"
}

/* -------------------------------- */
// API Weather
/* -------------------------------- */

data class WeatherBean(
    var coord: CoordBean,
    var visibility: Int,
    var name: String,
    @SerializedName("main")
    var temperature: TempBean,
    var wind : WindBean
)

data class WindBean(var speed : Double)

data class TempBean(var temp : Double)

data class CoordBean(var lon:Double, var lat: Double)

/* -------------------------------- */
// API POKEMON
/* -------------------------------- */
data class PokemonN1Bean(
    var name: String,
    var type: String
)

data class PokemonN2Bean(
    @SerializedName("Le nom du pokemon")
    var name: String,
    @SerializedName("son type")
    var type: String
)

data class PokemonN3Bean(
    var name: String,
    var type: List<String>
)