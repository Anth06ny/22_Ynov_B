package com.amonteiro.a22_ynov_b

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a22_ynov_b.databinding.ActivityFirebaseExampleBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth

class FirebaseExampleActivity : AppCompatActivity() {

    val binding by lazy { ActivityFirebaseExampleBinding.inflate(layoutInflater) }

    //On redirige le callback de connexion sur la méthode onSignInResult
    val signInLauncher = registerForActivityResult(FirebaseAuthUIActivityResultContract()) {
        //Pour afficher l'erreur s'il y en a une
        it.idpResponse?.error?.printStackTrace()
        refreshScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //clic
        binding.tvConnexion.setOnClickListener {
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )

            //Lance l'Activité de connexion
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.mipmap.ic_launcher)
                .build()

            //Lance la fenêtre de connexion de Firebase
            signInLauncher.launch(signInIntent)
        }

        binding.tvDeconnexion.setOnClickListener {
            //Deconnexion de firebase
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    refreshScreen()
                }
        }
    }

    //Appelée chaque fois que l'écran redevient visible
    override fun onStart() {
        super.onStart()
        refreshScreen()
    }

    fun refreshScreen() {

        val user = FirebaseAuth.getInstance().currentUser
        binding.tvName.text = user?.displayName ?: "---"
        binding.fab.isVisible = user != null

        if (user != null) {
            binding.tvConnexion.isVisible = false
            binding.tvDeconnexion.isVisible = true
        }
        else {
            binding.tvConnexion.isVisible = true
            binding.tvDeconnexion.isVisible = false
        }

    }
}