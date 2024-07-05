package com.example.proyecto_sisvita.data.model

data class UsuarioTipo (
    val id_usutip: Int = 0,
    val id_tipo: Int = 0,
    val id_usu: Int = 0,
    val sesion: Boolean = true,
    val condiciones: Boolean = true,
    val terminos: Boolean = true,
    val usuario: Paciente
)