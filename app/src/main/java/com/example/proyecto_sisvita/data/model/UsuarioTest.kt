package com.example.proyecto_sisvita.data.model

import java.util.Date

data class UsuarioTest(
    val id_test: Int = 0,
    val id_paciente: Int = 0,
    val pregunta: String ="",
    val respuesta: Int = 0,
    val nropregunta: Int = 0,
    val id_escala: Int = 0,
    val fecha_test: Date = Date(),
    val test: Test,
    val usuario_tipo: UsuarioTipo,
    val answers: List<Answers> = listOf()
)
