package com.example.minhaescola.dao

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable
import java.lang.Exception

@IgnoreExtraProperties
data class Avaliacao(val id_usuario: String? = "", var rate: Float? = 0f) : Serializable {
    override fun equals(other: Any?): Boolean {
        return try{
            val aux : Avaliacao = other as Avaliacao
            id_usuario.equals(aux.id_usuario)
        }catch (e: Exception){
            false
        }
    }
}
