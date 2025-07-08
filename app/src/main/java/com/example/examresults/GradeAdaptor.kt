package com.example.examresults

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class GradeAdaptor(private val mContext: Context, private val gradeList: List<Grade>) :
    RecyclerView.Adapter<GradeAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.card_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val grade = gradeList[position]
        holder.lesson.text = grade.name
        holder.score1.text = grade.score1.toString()
        holder.score2.text = grade.score2.toString()

        holder.card.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return gradeList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var card: CardView = view.findViewById(R.id.result_card)
        var lesson: TextView = view.findViewById(R.id.textView_Lesson)
        var score1: TextView = view.findViewById(R.id.textView_Result1)
        var score2: TextView = view.findViewById(R.id.textView_Result2)
    }

}