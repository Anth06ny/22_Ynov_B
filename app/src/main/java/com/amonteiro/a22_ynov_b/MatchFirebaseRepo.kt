package com.amonteiro.a22_ynov_b

import com.amonteiro.a22_ynov_b.exokotlin.MatchBean
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TABLE_NAME = "matches"
object MatchFirebaseRepo {

    //Pour éviter de réécrire tout à chaque fois
    private fun getCollection()= Firebase.firestore.collection(TABLE_NAME)

    //Pour ajouter un matche
    fun create(data: MatchBean) = getCollection().add(data)

}