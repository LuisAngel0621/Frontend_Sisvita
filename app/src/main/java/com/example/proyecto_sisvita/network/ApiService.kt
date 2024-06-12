package com.example.proyecto_sisvita.network

import com.example.proyecto_sisvita.data.model.Paciente
import com.example.proyecto_sisvita.data.model.PacienteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("/GuardarDatos")
    suspend fun datospacienteApiService(
        @Body paciente: Paciente
    ): Response<PacienteResponse>

    @POST("/Respuestas")
    suspend fun respuestaspacienteApiService(

    )


}