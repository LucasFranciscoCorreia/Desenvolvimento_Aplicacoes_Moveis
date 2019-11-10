package com.example.minhaescola.dao

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Rate(var rates: ArrayList<Avaliacao>?=ArrayList()) : Serializable