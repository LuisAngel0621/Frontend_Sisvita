package com.example.proyecto_sisvita.data.model

data class PacienteResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Paciente>
)
