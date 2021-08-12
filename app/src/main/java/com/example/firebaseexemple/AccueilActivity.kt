package com.example.firebaseexemple

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccueilActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)
        auth = Firebase.auth

        val user = auth.currentUser
        //val email = intent.getStringExtra("email_user")
        val tvEmail = findViewById<TextView>(R.id.TVemail)
        tvEmail.text = user?.uid

        val btretourconnecter = findViewById<Button>(R.id.btretourconnecter)
        val btdeconnecter = findViewById<Button>(R.id.btDeconnecter)
        val btverifiermail = findViewById<Button>(R.id.btverifmail)
        val btrefresh = findViewById<Button>(R.id.btrefresh)


        btretourconnecter.setOnClickListener {
            val retourintent = Intent(this,MainActivity::class.java)
            startActivity(retourintent)
        }
        btdeconnecter.setOnClickListener {
            auth.signOut()
        }

        btrefresh.setOnClickListener {
            val refreshintent = Intent(this,AccueilActivity::class.java)
            startActivity(refreshintent)
        }
        if (user != null){ //Si l'utilisateur existe càd s'il n'est pas null
            val emailVerified = user.isEmailVerified

            if (emailVerified == true){
                btverifiermail.visibility = View.GONE
            }
            else{
                btverifiermail.setOnClickListener {
                    user.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this,"Mail envoyé",Toast.LENGTH_LONG).show()
                            }
                        }
                }
            }
        }
    }
}