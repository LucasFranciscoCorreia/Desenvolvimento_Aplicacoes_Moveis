package com.example.usersrating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = usersRatingList

        var list: ArrayList<Rating> = ArrayList<Rating>()
        var i = 0
        while (i < 30) {
            list.add(Rating("User" + i, i, ""))
            i++
        }

        println(list)

        recyclerView.adapter = RatingAdapter(list , this)
    }
}
