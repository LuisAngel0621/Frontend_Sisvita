package com.example.proyecto_sisvita.data.model

data class UsuarioTest(
    val id_test: Int,
    val id_paciente: Int,
    val pregunta: String ="",
    val respuesta: Int,
    val nropregunta: Int,
    val id_escala: Int
)
