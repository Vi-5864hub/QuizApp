package com.example.quizapp.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.R
import com.example.quizapp.activities.adapter.QuizAdapter
import com.example.quizapp.activities.model.Quiz
import com.example.quizapp.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var actionBarDrawerToggle:ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore

    private var  quizList = mutableListOf<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()

    }

    fun setUpViews(){
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecyclerView()
        setUpDatePicker()
    }
    private fun setUpDatePicker(){
        binding.btnDatePicker.setOnClickListener {
            val datePicker=MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormatter.format(Date(it))
                val intent = Intent(this,QuestionActivity::class.java)
                 intent.putExtra("DATE" ,date)
                  startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER" , datePicker.headerText)
            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER","Date Picker Cancelled")
            }
        }
    }

    private fun setUpFireStore() {
         firestore =FirebaseFirestore.getInstance()
        val collectionReference =firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null){
                Toast.makeText(this,"Error fetching data" , Toast.LENGTH_SHORT).show()
                 return@addSnapshotListener
            }
            Log.d("DATA" , value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRecyclerView() {
        adapter= QuizAdapter(this,quizList)
        binding.quizRecyclerview.layoutManager = GridLayoutManager(this,2)
        binding.quizRecyclerview.adapter = adapter
    }

    fun setUpDrawerLayout(){
      setSupportActionBar(binding.AppBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.mainDrawer,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            binding.mainDrawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}