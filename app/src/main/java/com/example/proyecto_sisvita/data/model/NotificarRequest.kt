package com.example.proyecto_sisvita.data.model

data class NotificarRequest(
    val correo_especialista: String,
    val contraseña: String,
    val correo_paciente: String,
    val comentario: String,
    val recomendacion: String
)
