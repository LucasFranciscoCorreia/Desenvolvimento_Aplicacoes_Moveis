package com.example.minhaescola

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaescola.dao.Avaliacao
import com.example.minhaescola.dao.Escola
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.users_rating.*

class Rating: AppCompatActivity() {


    private inner class RequestRatesFromRemote(val id: Long?, val reciclerview : RecyclerView?, val context: Context) : AsyncTask<Void, Void, MutableList<Avaliacao>>(){
        val list : MutableList<Avaliacao> = ArrayList()
        override fun doInBackground(vararg p0: Void?): MutableList<Avaliacao> {
            val rates = FirebaseDatabase.getInstance().getReference("avaliacao/id_$id")
            rates.addValueEventListener( object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    for (child in p0.children) {
                        try {
                            val rate: Avaliacao = child.getValue(Avaliacao::class.java)!!
                            list.add(rate)
                        }catch (e:Exception){}
                    }
                    reciclerview?.adapter = RatingAdapter(list , context)
                }
                override fun onCancelled(p0: DatabaseError) {
                }
            })
            return list
        }
    }

    private lateinit var escola: Escola
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_rating)

        var escola = intent.extras?.getSerializable("escola") as Escola

        this.escola = escola

        RequestRatesFromRemote(escola.id, usersRatingList, this).execute()
    }

    override fun onResume(){
        super.onResume()
        RequestRatesFromRemote(escola.id, usersRatingList, this).execute()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
