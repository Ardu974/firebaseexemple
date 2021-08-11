package com.example.firebaseexemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ETemail = findViewById<EditText>(R.id.email)
        val ETpassword = findViewById<EditText>(R.id.password)
        val btConnexion = findViewById<Button>(R.id.btConnecter)
        val TVResultat = findViewById<TextView>(R.id.TVResultat)
        auth = Firebase.auth

        btConnexion.setOnClickListener {
            val email = ETemail.text
            val password = ETpassword.text

            auth.signInWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        TVResultat.text = "Connexion réussie"
                    } else {
                        TVResultat.text = "Echec de connexion"
                    }
                }
        }
    }
}