package com.example.quizapp.activities.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.activities.activities.QuestionActivity
import com.example.quizapp.activities.model.Quiz
import com.example.quizapp.activities.utils.ColorPicker
import com.example.quizapp.activities.utils.iconPicker

class QuizAdapter(val context:Context, val quizzes: List<Quiz>):RecyclerView.Adapter<QuizAdapter.QuizViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
           val view= LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
          return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(iconPicker.getIcon())
        holder.itemView.setOnClickListener {
            Toast.makeText(context,quizzes[position].title, Toast.LENGTH_SHORT).show()

            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE", quizzes[position].title)
            context.startActivity(intent)

        }
    }

inner class QuizViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
    var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
    var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)

}
}