package com.amonteiro.a22_ynov_b.exokotlin


//Extension pour éviter d'avoir à faire plusieurs fois l'affichage
fun Iterable<UserBean>.print() = println(this.joinToString("\n") { it.name + " : " + it.note })

fun main() {

    val users = arrayListOf(
        UserBean("toto", 5),
        UserBean("toto", 5),
        UserBean("toto", 5),
        UserBean("Bobby", 20),
        UserBean("Charles", 14)
    )

//    Afficher la list triée par nom
    //println(users.sortedBy { it.name.lowercase() })

//            Afficher la sous liste de personne ayant 10 et +
    println(users.filter { it.note >= 10 })

//    Afficher combien il y a de Toto dans la classe ?
    val lambdaIsToto = { it: UserBean -> it.name.equals("Toto", true) }
    println(users.count { it.name.equals("Toto", true) })


//    Afficher combien de Toto ayant la moyenne (10 et +)
    println(users.count { lambdaIsToto(it) && it.note >= 10 })


//    Afficher combien de Toto ont plus que la moyenne de la classe
    val moyenne = users.map { it.note }.average()
    println(users.count { lambdaIsToto(it) && it.note >= moyenne })

//            Ajouter un point a ceux n’ayant pas la moyenne (<10)
    users.filter { it.note < 10 }.forEach { it.note++ }

//    Ajouter un point à tous les Toto
    users.filter(lambdaIsToto).forEach { it.note++ }

//    Retirer de la liste ceux ayant la note la plus petite. (Il faut une ArrayList)
    val note = users.minOfOrNull { it.note }
    users.removeIf { it.note == note }

//    Afficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique
    println(users.filter { it.note > 10 }.sortedBy { it.name.lowercase() }.map { it.name })

}


fun exo2() {
    val compareUsersByNote: (UserBean, UserBean) -> UserBean = { u1, u2 -> if (u1.note > u2.note) u1 else u2 }
    val compareUsersByName: (UserBean, UserBean) -> UserBean = { u1, u2 -> if (u1.name.lowercase() > u2.name.lowercase()) u1 else u2 }


}

inline fun compareUsers(lambda: (UserBean, UserBean) -> UserBean, u1: UserBean, u2: UserBean, u3: UserBean) = lambda(lambda(u1, u2), u3)

fun exo1() {
    val lambdaLower: (String) -> Unit = { texte: String -> println(texte.lowercase()) }
    val lambdaLowerV2 = { texte: String -> println(texte.lowercase()) }
    val lambdaLowerv3: (String) -> Unit = { texte -> println(texte.lowercase()) }
    val lambdaLowerv4: (String) -> Unit = {
        println(it.lowercase())
    }

    lambdaLower("Une phrase")
}

