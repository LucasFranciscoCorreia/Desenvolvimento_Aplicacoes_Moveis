package com.example.usersrating

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rating_fragment.view.*

class RatingAdapter(private val rates: List<Rating>, private val context: Context): RecyclerView.Adapter<RatingAdapter.ViewHolder>(){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rate : Rating = rates.get(position)
        holder.bindView(rate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rating_fragment, parent, false))
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    class  ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){

        fun bindView(note: Rating) {
            var user = itemView.nomeUsuario
            var rate = itemView.ratingValue
            var comment = itemView.comentario

            user.text = note.usuario
            rate.numStars = note.classificacao
            comment.text = note.comentario
        }
    }
}