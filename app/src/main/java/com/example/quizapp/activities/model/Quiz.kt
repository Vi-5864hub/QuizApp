package com.example.quizapp.activities.model

import com.example.quizapp.activities.model.Question

data class Quiz(
    var id: String = "",
    var title : String="",
    var questions: MutableMap<String, Question> = mutableMapOf()
)
