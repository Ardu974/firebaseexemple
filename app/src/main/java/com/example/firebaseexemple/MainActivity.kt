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

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ETemail = findViewById<EditText>(R.id.email)
        val ETpassword = findViewById<EditText>(R.id.password)
        val btConnexion = findViewById<Button>(R.id.btConnecter)
        val TVResultat = findViewById<TextView>(R.id.TVResultat)
        val btRegister = findViewById<Button>(R.id.btRegister)

        btRegister.setOnClickListener {
            val EnregistrementIntent = Intent(this,EnregistrementActivity::class.java)
            startActivity(EnregistrementIntent)
        }
        auth = Firebase.auth
        val user = auth.currentUser
            TVResultat.text = user?.uid
        val emailEnregistrement = intent.getStringExtra("email_user")

        if (emailEnregistrement != null){
            ETemail.setText(emailEnregistrement)
        }

        btConnexion.setOnClickListener {
            val email = ETemail.text.toString()
            val password = ETpassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val accueilintent = Intent(this,AccueilActivity::class.java)
                        accueilintent.putExtra("email_user",email)
                        startActivity(accueilintent)
                    } else {
                        TVResultat.text = "Echec de connexion"
                    }
                }
        }
    }
}