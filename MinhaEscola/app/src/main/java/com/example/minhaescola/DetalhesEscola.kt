package com.example.minhaescola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.TextView
import com.example.minhaescola.dao.Escola


class DetalhesEscola : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detalhes_escola)
        var nome_escola : TextView = findViewById(R.id.nome_escola)
        var escola = intent.extras?.getSerializable("escola") as Escola
        nome_escola.text = escola.properties?.escola_nome
    }

    fun avaliar(view:View?) {
        val i = Intent(this, activity_avaliacao::class.java)
        startActivity(i)
    }
}
