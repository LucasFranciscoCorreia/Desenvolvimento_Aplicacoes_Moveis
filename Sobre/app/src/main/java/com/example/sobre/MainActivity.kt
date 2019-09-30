package com.example.sobre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        print("Minha Escola é uma aplicativo para auxilio escolar, onde pais podem analisar o desempenho")
        println("de seus filhos e o mnicipio pode analisar o desenpenho de suas escolas.")
        print(" O nosso proposito é criar um ranking entre as escolas para destacar e mostrar escolas de referencia")
        println(" em questão de qualidade de ensino, infraestrutura, segurança, etc, além de estimular as escolas a")
        print("melhorar sua qualidade.")
    }


}
