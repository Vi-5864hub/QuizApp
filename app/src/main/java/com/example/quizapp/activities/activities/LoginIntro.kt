package com.example.quizapp.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityLoginIntroBinding
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
     private lateinit var binding :ActivityLoginIntroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth : FirebaseAuth = FirebaseAuth.getInstance()

        if (auth.currentUser != null){
            Toast.makeText(this,"User is already logged in !", Toast.LENGTH_SHORT).show()
            redirect("Main")
        }
        binding.btnGetStarted.setOnClickListener {
            redirect("Login")
        }
    }

    private  fun redirect(name:String){
        val intent : Intent = when(name){
            "Login" -> Intent(this, LoginActivity::class.java)
            "Main" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")

        }
        startActivity(intent)
        finish()
    }
}