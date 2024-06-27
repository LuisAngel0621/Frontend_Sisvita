package com.example.proyecto_sisvita.data.model

import java.util.Date

data class Ubigeo(
    val id_ubigeo: Int,
    val id_usu: Int,
    val latitud: Int,
    val longitud: Int,
    val nivel_ansiedad: String,
    val fecha: Date
)
