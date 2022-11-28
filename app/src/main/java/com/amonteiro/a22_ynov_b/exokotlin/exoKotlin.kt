package com.amonteiro.a22_ynov_b.exokotlin

fun main() {


    var toto: String? = "coucou"


    var res = boulangerie(5, nbSandwitch = 6)
    println("res=$res")

}

fun boulangerie(nbCroissant : Int = 0, nbBaguette : Int = 0 , nbSandwitch : Int = 0)  =
            nbBaguette * PRIX_BAGUETTE + nbCroissant * PRIX_CROISSANT + nbSandwitch * PRIX_SANDWITCH





fun min(a:Int, b:Int, c:Int) = if(a <= b && a <= c) a else if(b <= a && b <= c) b else c
fun pair(c:Int) = c %2 == 0
fun myPrint(texte : String) = println("##$texte##")




