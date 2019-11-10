package com.example.minhaescola.dao

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Propriedades (
    var atendimento_especial : String? = null,
    var endereco : String? = null,
    var escola_codigo: Long? = -1,
    var escola_nome: String? = null,
    var escola_tipo : String? = null,
    var existe_biblioteca: String? = null,
    var existe_quadra : String? = null,
    var laboratorio_informatica: Int? = -1,
    var mec_codigo : Int? = -1,
    var prog: String?  = null,
    var qtd_anexos: Int? = -1,
    var qtd_atendidos: Int? = -1,
    var rpa: Int? = -1,
    var sala_professora: String? = null,
    var tipo_predio: String? = null
) : Serializable
