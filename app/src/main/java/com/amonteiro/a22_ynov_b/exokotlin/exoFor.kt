package com.amonteiro.a22_ynov_b.exokotlin


fun main() {
    var res = nbA("Une chaine avec des a")
    println("res=$res")

    arrayListOf("","","").filter {
         it ==""
    }
}

fun retirerEspaceDebutV2(s: String) = s.trimStart()

fun retirerEspaceDebut(s: String): String {
    var garderEspace = false
    var sortie = ""
    for (c in s) {
        if (c != ' ') {
            sortie += c
            garderEspace = true
        }
        else if (garderEspace) {
            sortie += c
        }
    }
    return sortie
}

fun plusGranCharV2(s: String): Char? = s.maxOrNull()

fun plusGrandChar(s: String): Char? {
    var max: Char? = null
    for (c in s) {
        if (max == null || c > max) {
            max = c
        }
    }
    return max
}

const val VOYELLES = "aeiouyAEIOUY"

fun sanslesVoyelleLambda(texte: String)  = texte.filter { it !in VOYELLES }

fun sanslesVoyelle(texte: String): String {
    var sortie = ""

    for (c in texte) {
        if (c !in VOYELLES) {
            sortie += c
        }
    }
    return sortie
}

fun reverseLambda(s:String) = s.reversed()

fun reverseV2(s: String): String {
    var result = ""
    for (i in s.length-1 downTo 0) {
        result += s[i]
    }
    return result
}

fun reverse(s: String): String {
    var result = ""
    for (c in s) {
        result = c + result
    }
    return result
}

fun nbALambda(s: String) = s.count { it in "aAà" }

fun nbA(s: String): Int {
    var result = 0

//    for (i in s.indices) {
//        if (s[i] in "aAà") {
//            result++
//        }
//    }

    //foreach
    for (c in s) {
        if (c in "aAà") {
            result++
        }
    }
    return result
}