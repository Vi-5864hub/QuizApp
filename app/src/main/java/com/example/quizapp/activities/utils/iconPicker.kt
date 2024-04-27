package com.example.quizapp.activities.utils

import com.example.quizapp.R

object iconPicker {
    val  icons = arrayOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4,
        R.drawable.img_5,
        R.drawable.img
    )
    var currentIcon = 0

    fun getIcon(): Int{
        currentIcon = (currentIcon +1) % icons.size
        return icons[currentIcon]
    }
}