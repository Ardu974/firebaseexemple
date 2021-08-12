package com.example.firebaseexemple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EnregistrementActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enregistrement)
        val ETemail = findViewById<EditText>(R.id.Eemail)
        val ETpassword = findViewById<EditText>(R.id.Epassword)
        val btConnexion = findViewById<Button>(R.id.btInscrire)
        val TVResultat = findViewById<TextView>(R.id.ETVResultat)
        auth = Firebase.auth

        btConnexion.setOnClickListener {
            val email = ETemail.text.toString()
            val password = ETpassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val mainintent = Intent(this, MainActivity::class.java)
                        //mainintent.putExtra("email_user", email)
                        startActivity(mainintent)
                    } else {
                        TVResultat.text = "Echec de connexion"
                    }
                }
        }
    }
}