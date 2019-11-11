package com.example.minhaescola

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaescola.dao.Avaliacao
import kotlinx.android.synthetic.main.fragment_rating.view.*

class RatingAdapter(private val rates: List<Avaliacao>, private val context: Context): RecyclerView.Adapter<RatingAdapter.ViewHolder>(){

    private var i = 0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rate : Avaliacao = rates.get(position)
        holder.bindView(rate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_rating, parent, false))
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    class  ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){

        fun bindView(note: Avaliacao) {
            var rate = itemView.ratingValue

            rate.rating = note.rate!!
        }
    }
}