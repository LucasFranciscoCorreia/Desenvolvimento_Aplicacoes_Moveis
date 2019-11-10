package com.example.minhaescola.dao

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Localizacao(
    var coordinates: ArrayList<ArrayList<ArrayList<Double>>>? = null,
    var type: String? = null
) : Serializable