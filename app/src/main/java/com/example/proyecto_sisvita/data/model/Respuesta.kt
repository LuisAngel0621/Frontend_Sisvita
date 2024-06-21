package com.example.proyecto_sisvita.data.model

data class Respuesta(
    val descripcion: String,
    val id_respuestas: Int,
){
    fun toArrayList(): ArrayList<String>{
        return arrayListOf(
            descripcion,
            id_respuestas.toString()
        )
    }
}
