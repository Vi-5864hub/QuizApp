package com.example.quizapp.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.R
import com.example.quizapp.activities.model.Quiz
import com.example.quizapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth =FirebaseAuth.getInstance()
          binding.tvEmail.text = firebaseAuth.currentUser?.email

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}