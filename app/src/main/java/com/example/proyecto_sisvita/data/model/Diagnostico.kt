package com.example.proyecto_sisvita.data.model

import java.util.Date
data class Diagnostico(
    val comentario: String ="",
    val id_diag: Int = 0,
    val id_usutip: Int = 0, //Actualizacion
    val id_user_test: Int=0,
    val id_nivel: Int = 0,
    val notificacion: Boolean = false,
    val puntaje: Int=0,
    val recomendacion: String = "",
    val tipo_nivel: Nivel,
    val usuario_test: UsuarioTest
)



