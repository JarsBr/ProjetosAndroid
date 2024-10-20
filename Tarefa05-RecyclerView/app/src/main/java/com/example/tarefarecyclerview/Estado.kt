package com.example.tarefarecyclerview

import java.io.Serializable

data class Estado(
    val bandeiraResId: Int,
    val nome: String,
    val capital: String,
    val populacao: Int,
    val regiao: String
) : Serializable
