package com.example.proyecto_sisvita.network

import com.example.proyecto_sisvita.data.model.Diagnostico
import com.example.proyecto_sisvita.data.model.DiagnosticoRes
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
import com.example.proyecto_sisvita.data.model.SumaPuntaje
import com.example.proyecto_sisvita.data.model.UbigeoResponse
import com.example.proyecto_sisvita.data.model.UsuarioTest
import com.example.proyecto_sisvita.data.model.UsuarioTestResponse
import com.example.proyecto_sisvita.data.model.UsuarioTipo
import com.example.proyecto_sisvita.data.model.UsuarioTipoResponse
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

    @POST("/EnviarContrasenia")
    suspend fun enviarcontrasenia(
        @Body usuarioTipo: UsuarioTipo
    ): Response<UsuarioTipoResponse>

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

    @GET("/SumaPuntaje/{id_paciente}")
    suspend fun sumapuntaje(
        @Path("id_paciente") id_paciente: Int,
    ): Response<SumaPuntaje>

    @POST("/ResultadoTest")
    suspend fun diagnosticoprevio(
        @Body diagnostico: Diagnostico
    ): Response<DiagnosticoRes>

    //CUS Realizar Vigilancia
    @GET("/RealizarVigilancia")
    suspend fun realizarvigilancia(): Response<DiagnosticoResponse>

    @GET("/RealizarVigilancia/{id_diag}")
    suspend fun realizarVigilanciaEspecifica(
        @Path("id_diag")id_diag: Int,
    ): Response<DiagnosticoRes>

    @GET("/ObtenerNivel/{id_nivel}")
    suspend fun obtenerNivelEspecifico(
        @Path("id_nivel")id_diag: Int,
    ): Response<NivelResponse>

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