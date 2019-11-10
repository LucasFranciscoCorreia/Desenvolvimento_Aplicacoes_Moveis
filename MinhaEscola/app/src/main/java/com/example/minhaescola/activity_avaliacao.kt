package com.example.minhaescola

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.example.minhaescola.dao.Avaliacao
import com.example.minhaescola.dao.Escola
import com.example.minhaescola.dao.Rate
import com.example.minhaescola.ui.home.HomeFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception

class activity_avaliacao : AppCompatActivity() {

    private lateinit var escola: Escola

    private inner class PostRatesToRemote(
        val id: Long?,
        val avaliacao: Avaliacao,
        val activityAvaliacao: activity_avaliacao
    ) : AsyncTask<Void, Void, MutableList<Avaliacao>>(){
        var list : MutableList<Avaliacao> = ArrayList()
        override fun doInBackground(vararg p0: Void?): MutableList<Avaliacao> {
            val rates = FirebaseDatabase.getInstance().getReference("avaliacao/id_$id")
            rates.addListenerForSingleValueEvent( object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    for (child in p0.children) {
                        try {
                            val rate: Avaliacao = child.getValue(Avaliacao::class.java)!!
                            list.add(rate)
                        }catch(e: Exception){

                        }
                    }
                    list.add(avaliacao)
                    Log.i("post:", "going")
                    val ref = FirebaseDatabase.getInstance().getReference("avaliacao").child("id_$id")
                    val key = ref.push().key
                    ref.setValue(list)
//                    activityAvaliacao.voltarMapa()
                    finish()
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
            return list
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avaliacao)
        escola = intent.extras?.getSerializable("escola") as Escola
        val btnAvaliar : Button = findViewById(R.id.btnAvaliar)
        btnAvaliar.setOnClickListener {
            avaliar()
        }
    }

    fun voltarMapa(){
//        val mapa = Intent(applicationContext, HomeFragment::class.java)
//        startActivity(applicationContext, mapa, null)
    }

    fun avaliar(){
        val id : Long? = escola?.id

        val ratingbar: RatingBar = findViewById(R.id.ratingQuality)

        val rate = Avaliacao(id, ratingbar.rating)

        PostRatesToRemote(id, rate, this).execute()
//        FirebaseDatabase.getInstance().getReference("avaliacao").push().setValue(ratelist)


    }


}
