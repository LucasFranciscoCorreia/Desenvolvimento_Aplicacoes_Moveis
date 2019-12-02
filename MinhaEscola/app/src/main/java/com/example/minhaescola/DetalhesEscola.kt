package com.example.minhaescola

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.minhaescola.dao.Avaliacao

import com.example.minhaescola.dao.Escola
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception

class DetalhesEscola : AppCompatActivity() {


    private inner class RequestRatesFromRemote(val list: MutableList<Avaliacao>, val id: Long?, val bar: RatingBar) : AsyncTask<Void, Void, MutableList<Avaliacao>>(){
        override fun doInBackground(vararg p0: Void?): MutableList<Avaliacao> {
            val rates = FirebaseDatabase.getInstance().getReference("avaliacao/id_$id")
            rates.addValueEventListener( object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    var stars = 0f
                    for (child in p0.children) {
                        try {
                            val rate: Avaliacao = child.getValue(Avaliacao::class.java)!!
                            if(!list.contains(rate))
                                list.add(rate)
                            else
                                list.get(list.indexOf(rate)).rate = rate.rate
                            stars += rate.rate!!
                        }catch (e:Exception){}
                    }
                    var rate : Float
                    if(list.size != 0)
                        rate = stars/list.size
                    else
                        rate = 5f

                    bar.rating = rate
                    Log.i("rates:", "rate is $rate")
                }
                override fun onCancelled(p0: DatabaseError) {
                }
            })
            return list
        }
    }

    private lateinit var escola: Escola
    private var list: MutableList<Avaliacao> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detalhes_escola)
        val nome_escola : TextView = findViewById(R.id.nome_escola)
        val endereco : TextView = findViewById(R.id.endereco)
        var escola = intent.extras?.getSerializable("escola") as Escola
        nome_escola.text = escola.properties?.escola_nome
        endereco.text = escola.properties?.endereco
        this.escola = escola
        RequestRatesFromRemote(list, escola.id, findViewById(R.id.ratingResult)).execute()

    }

    override fun onResume(){
        super.onResume()
        RequestRatesFromRemote(list, escola.id, findViewById(R.id.ratingResult)).execute()
    }

    fun carregarTelaAvaliar(view:View?) {
        val i = Intent(this, activity_avaliacao::class.java)
        var user_rate : Avaliacao? = null
        for(rate :Avaliacao in list){
            if(rate.id_usuario.equals(MainActivity.user)) {
                user_rate = rate
                break
            }
        }
        i.putExtra("escola", escola)
        i.putExtra("user", user_rate)
        startActivity(i)
    }

    fun carregarTelaComentarios(view:View?){
        val i = Intent(this, Rating::class.java)
        i.putExtra("escola", escola)
        startActivity(i)
    }
}
