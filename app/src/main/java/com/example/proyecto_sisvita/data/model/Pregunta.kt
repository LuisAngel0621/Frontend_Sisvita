package com.example.proyecto_sisvita.data.model

data class Pregunta (
    val descripcion: String,
    val id_preguntas: Int
){
    fun toArrayList(): ArrayList<String>{
        return arrayListOf(
            descripcion,
            id_preguntas.toString()
        )
    }
}
