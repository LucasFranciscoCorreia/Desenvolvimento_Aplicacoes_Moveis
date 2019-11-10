package com.example.minhaescola.dao

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Avaliacao(val id_escola: Long? = -1, val rate: Float? = 0f) : Serializable
