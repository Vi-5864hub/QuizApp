package com.example.quizapp.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
          lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
          firebaseAuth =FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
            finish()
        }

    }

    private  fun login(){
        val email = binding.editTextTextEmailAddress.text.toString()
        val password= binding.editTextTextPassword.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Email/Password can't be blank" , Toast.LENGTH_SHORT).show()
            return
        }


       firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this)
       {
           if (it.isSuccessful){
               Toast.makeText(this, "Success" , Toast.LENGTH_SHORT).show()
               val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
               finish()
           }
           else {
               Toast.makeText(this,"Authentication Failed" , Toast.LENGTH_SHORT).show()

           }
       }
    }
}