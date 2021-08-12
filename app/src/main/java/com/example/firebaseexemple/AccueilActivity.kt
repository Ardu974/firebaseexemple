package com.example.firebaseexemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AccueilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)

        val email = intent.getStringExtra("email_user")
        val tvEmail = findViewById<TextView>(R.id.TVemail)
        tvEmail.text = email
    }
}