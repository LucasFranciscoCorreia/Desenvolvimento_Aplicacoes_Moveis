package com.example.minhaescola

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.minhaescola.dao.Avaliacao
import com.example.minhaescola.dao.Escola
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class activity_avaliacao : AppCompatActivity() {

    private inner class PostRatesToRemote(val id: Long?, val avaliacao: Avaliacao) : AsyncTask<Void, Void, MutableList<Avaliacao>>(){
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
                    if(!list.contains(avaliacao))
                        list.add(avaliacao)
                    else{
                        for(rate: Avaliacao in list) {
                            if (rate == avaliacao) {
                                rate.rate = avaliacao.rate
                                break
                            }
                        }
                    }
                    val ref = FirebaseDatabase.getInstance().getReference("avaliacao").child("id_$id")
//                    val key = ref.push().key
                    ref.setValue(list)
                    finish()
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
            return list
        }
    }

    private lateinit var escola: Escola
    private var rate: Avaliacao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avaliacao)
        escola = intent.extras?.getSerializable("escola") as Escola
        rate = intent.extras?.getSerializable("user") as Avaliacao?
        val btnAvaliar : Button = findViewById(R.id.btnAvaliar)
        if(rate != null)
            findViewById<RatingBar>(R.id.ratingQuality).rating = rate?.rate!!
        btnAvaliar.setOnClickListener {
            avaliar()
        }
    }

    fun avaliar(){
        val id : Long? = escola?.id

        val ratingbar: RatingBar = findViewById(R.id.ratingQuality)
        if(rate == null) {
            val rate = Avaliacao(MainActivity.user, ratingbar.rating)

            PostRatesToRemote(id, rate).execute()
        }else{
            rate?.rate = ratingbar.rating
            PostRatesToRemote(id, rate!!).execute()
        }
    }
}
