package com.example.quizapp.activities.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.quizapp.R
import com.example.quizapp.activities.model.Quiz
import com.example.quizapp.databinding.ActivityResultBinding
import com.google.gson.Gson
import java.lang.StringBuilder

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
    }

    private fun setUpViews() {
        val quizData =intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)
        calculatesScore()
        setAnswerView()
    }

    private fun setAnswerView() {
            val builder = StringBuilder("")
            for (entry in quiz.questions.entries){
                val question =entry.value
                builder.append("<font color'#18206'><b>Question:${question.description}</b>")
                builder.append("<font color='#009688'>Answer:${question.answer}</font><br/><br/>")
            }
         if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
             binding.tvScore.text =Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT)
         }else{
             binding.tvScore.text = Html.fromHtml(builder.toString())
         }
    }

    private fun calculatesScore() {
        var score = 0
        for (entry in quiz.questions.entries){
            val question =entry.value
            if (question.answer == question.userAnswer){
                score +=10
            }
        }

    }
}