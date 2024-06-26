package com.example.proyecto_sisvita.network

import com.example.proyecto_sisvita.data.model.DiagnosticoResponse
import com.example.proyecto_sisvita.data.model.EscalaResponse
import com.example.proyecto_sisvita.data.model.EvaluarTestRequest
import com.example.proyecto_sisvita.data.model.EvaluarTestResponse
import com.example.proyecto_sisvita.data.model.LoginRequest
import com.example.proyecto_sisvita.data.model.LoginResponse
import com.example.proyecto_sisvita.data.model.NivelResponse
import com.example.proyecto_sisvita.data.model.NotificarRequest
import com.example.proyecto_sisvita.data.model.NotificarResponse
import com.example.proyecto_sisvita.data.model.Paciente
import com.example.proyecto_sisvita.data.model.PacienteResponse
import com.example.proyecto_sisvita.data.model.PreguntaResponse
import com.example.proyecto_sisvita.data.model.RespuestaResponse
import com.example.proyecto_sisvita.data.model.UbigeoResponse
import com.example.proyecto_sisvita.data.model.UsuarioTest
import com.example.proyecto_sisvita.data.model.UsuarioTestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {

    //CUS LOGIN
    @POST("/Sesion")
    suspend fun iniciarSesion(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    //CUS Registro de Paciente
    @POST("/RegistrarUsuario")
    suspend fun datospacienteApiService(
        @Body paciente: Paciente
    ): Response<PacienteResponse>

    //CUS Realizar Test
    @POST("/RealizarTest")
    suspend fun respuestaspacienteApiService(
        @Body usuarioTest: UsuarioTest
    ): Response<UsuarioTestResponse>

    @GET("/ObtenerPreguntas")
    suspend fun obtenerPreguntas(): Response<PreguntaResponse>

    @GET("/ObtenerRespuestas")
    suspend fun obtenerRespuestas(): Response<RespuestaResponse>

    @GET("/ObtenerEscala")
    suspend fun obtenerEscala(): Response<EscalaResponse>

    //CUS Realizar Vigilancia
    @GET("/RealizarVigilancia")
    suspend fun realizarvigilancia(): Response<DiagnosticoResponse>

    @GET("/ObtenerNivel")
    suspend fun obtenerNivel(): Response<NivelResponse>

    //CUS Evaluar Test
    @PUT("/EvaluarTest/{id_diag}")
    suspend fun evaluatest(
        @Path("id_diag") id_diag: Int,
        @Body testRequest: EvaluarTestRequest
    ): Response<EvaluarTestResponse>

    @POST("/NotificarResultado")
    suspend fun notificarresult(
        @Body notificarRequest: NotificarRequest
    ): Response<NotificarResponse>

    //CUS Visualizar Mapa de Calor
    @GET("/VisualizarMapa")
    suspend fun visualizarmapa(): Response<UbigeoResponse>
}