package com.example.proyecto_sisvita.data.model

data class UsuarioTipo (
    val id_usutip: Int = 0,
    val id_tipo: Int,
    val id_usu: Int,
    val sesion: Boolean,
    val condiciones: Boolean,
    val terminos: Boolean,
    val usuario: Paciente
){

}