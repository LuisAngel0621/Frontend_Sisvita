package com.example.proyecto_sisvita.data.model

data class Paciente (
    val id_usu: Int = 0,//Actualizacion
    val nombres: String ="",
    val apellidos: String ="",
    val correoinstitucional: String ="",
    val edad: Int =0,
    val sexo: String ="",
    val estadocivil: String ="",
    val ocupacion: String ="",
    val ubigeo: Ubigeo
)

