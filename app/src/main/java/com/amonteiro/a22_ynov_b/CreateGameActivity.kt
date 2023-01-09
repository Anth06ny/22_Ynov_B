package com.amonteiro.a22_ynov_b

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a22_ynov_b.databinding.ActivityCreateGameBinding
import com.amonteiro.a22_ynov_b.exokotlin.MatchBean
import java.util.*

class CreateGameActivity : AppCompatActivity() {

    val binding by lazy { ActivityCreateGameBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val match = MatchBean(null, binding.etTeam1.text.toString(), binding.etTeam2.text.toString(), null, 0, 0, Date().getTime())


            MatchFirebaseRepo.create(match).addOnSuccessListener {
                //Ca fonctionne
                Toast.makeText(this, "Matche ajouté id=${it.id}", Toast.LENGTH_LONG).show()
                finish()

            }.addOnFailureListener {
                binding.tvError.text = "Erreur lors de la création du matche : ${it.message}"
                it.printStackTrace()
            }

        }


    }
}