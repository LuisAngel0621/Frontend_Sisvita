package com.example.proyecto_sisvita.data.model

import java.util.Date

data class UsuarioTest(
    val id_test: Int,
    val id_paciente: Int,
    val pregunta: String ="",
    val respuesta: Int,
    val nropregunta: Int,
    val id_escala: Int,
    val fecha_test: Date,
    val test: Test,
    val usuario_tipo: UsuarioTipo
)
