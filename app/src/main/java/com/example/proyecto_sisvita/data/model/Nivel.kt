package com.example.proyecto_sisvita.data.model


data class Nivel(
    val id_nivel: Int = 0,
    val rang_min: Int = 0,
    val rang_max: Int = 0,
    val nivel_ansiedad: String = ""
){
    fun toArrayList(): ArrayList<String> {
        return arrayListOf(
            nivel_ansiedad
        )
    }
}

