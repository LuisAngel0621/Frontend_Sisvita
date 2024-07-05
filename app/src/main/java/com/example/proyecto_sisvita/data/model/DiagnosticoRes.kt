package com.example.proyecto_sisvita.data.model

data class DiagnosticoRes (
    val success: Boolean,
    val data: Diagnostico,// Cambia a una lista de Diagnostico
    var id_diag: Int
){
}