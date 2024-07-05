package com.example.proyecto_sisvita.data.model

data class DiagnosticoResponse(
    val success: Boolean,
    val data: List<Diagnostico>,// Cambia a una lista de Diagnostico
    //val id_diag: Int
)
