package com.example.proyecto_sisvita.data.model

data class Respuestas(
    val question: String,
    var selectedOption: Int = -1,
    var selectedScale: Int = -1
)
