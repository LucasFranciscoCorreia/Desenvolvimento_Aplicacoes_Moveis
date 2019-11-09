package com.example.minhaescola.dao

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Localizacao(
    var coordinates: ArrayList<ArrayList<ArrayList<Double>>>? = null,
    var type: String? = null
)