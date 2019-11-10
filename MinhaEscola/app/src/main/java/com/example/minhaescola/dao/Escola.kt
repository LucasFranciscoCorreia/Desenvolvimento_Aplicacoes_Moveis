package com.example.minhaescola.dao

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable


@IgnoreExtraProperties
data class Escola (
    var geometry : Localizacao? = null,
    var id: Long? = -1,
    var properties: Propriedades? = null
) : Serializable