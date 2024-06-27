package com.example.proyecto_sisvita.data.model

import java.util.Date

data class Diagnostico(
    val id_diag: Int,
    val id_usutip: Int,
    val id_nivel: Int,
    val nombres: String = "",
    val apellidos: String = "",
    val tipo_test: String = "",
    val puntaje: Int,
    val comentario: String ="",
    val recomendacion: String = "",
    val notificacion: Boolean,
    val fecha_test: Date
)
