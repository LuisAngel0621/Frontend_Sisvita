package com.example.proyecto_sisvita.data.model


data class Nivel(
    val id_nivel: Int,
    val rang_min: Int,
    val rang_max: Int,
    val nivel_ansiedad: String
){
    fun toArrayList(): ArrayList<String> {
        return arrayListOf(
            nivel_ansiedad
        )
    }
}

