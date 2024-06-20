package com.example.proyecto_sisvita.network

import com.example.proyecto_sisvita.data.model.Paciente
import com.example.proyecto_sisvita.data.model.PacienteResponse
import com.example.proyecto_sisvita.data.model.PreguntaResponse
import com.example.proyecto_sisvita.data.model.UsuarioTest
import com.example.proyecto_sisvita.data.model.UsuarioTestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @POST("/RealizarTest")
    suspend fun respuestaspacienteApiService(
        @Body usuarioTest: UsuarioTest
    ): Response<UsuarioTestResponse>


    @POST("/RegistrarUsuario")
    suspend fun datospacienteApiService(
        @Body paciente: Paciente
    ): Response<PacienteResponse>

    @GET("/ObtenerPreguntas")
    suspend fun obtenerPreguntas(): Response<PreguntaResponse>

}