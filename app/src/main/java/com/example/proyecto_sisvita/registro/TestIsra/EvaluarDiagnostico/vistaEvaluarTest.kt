package com.example.proyecto_sisvita.registro.TestIsra.EvaluarDiagnostico

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.proyecto_sisvita.data.model.EvaluarTestRequest
import com.example.proyecto_sisvita.data.model.NotificarRequest
import com.example.proyecto_sisvita.network.GlobalState
import com.example.proyecto_sisvita.viewmodel.EvaluarViewModel
import com.example.proyecto_sisvita.viewmodel.LoginViewModel
import com.example.proyecto_sisvita.viewmodel.VigilanciaViewModel
import kotlinx.coroutines.delay

@Composable
fun EvaluarTestScreen(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    vigilanciaViewModel: VigilanciaViewModel,
    evaluarViewModel: EvaluarViewModel,
    loginViewModel: LoginViewModel,
    id: Int
){
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }
    var estaEvaluado by remember { mutableStateOf(false) }
    var showPopupEva by remember { mutableStateOf(false) }
    var showPopupNoti by remember { mutableStateOf(false) }
    //Inicializamos la vista entregando el ID
    vigilanciaViewModel.realizarVigilanciaEspecifica(id)

    //Hasta que no se cargue la vista mostrara una pantalla de carga
    LaunchedEffect(key1 = true) {
        delay(2000)
        if (vigilanciaViewModel.diagnostico.value != null) {
            isLoading = false
        }
    }
    //Cargamos los valores
    val diagnostico = vigilanciaViewModel.diagnostico.value

    if (!isLoading) {
        val nombre = "${diagnostico?.usuario_test?.usuario_tipo?.usuario?.nombres} ${diagnostico?.usuario_test?.usuario_tipo?.usuario?.apellidos}"
        val nivel_ansiedad = diagnostico?.tipo_nivel?.nivel_ansiedad
        var comentarios by remember { mutableStateOf("") }
        var recomendaciones by remember { mutableStateOf("") }
        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(Color(0xFFE0F7FA))
            ) {
                Text(
                    text = "Evaluar al paciente",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                )
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "\t\tNombre:\t\t\t$nombre",
                        style = TextStyle(
                            fontSize = 18.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                    Text(
                        text = "\t\tNivel:\t\t\t$nivel_ansiedad",
                        style = TextStyle(
                            fontSize = 18.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                    Text(
                        text = "\t\tComentarios:",
                        style = TextStyle(
                            fontSize = 18.sp,
                        )
                    )
                    TextField(
                        value = comentarios,
                        onValueChange = { comentarios = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                    Text(
                        text = "\t\tRecomendacion:",
                        style = TextStyle(
                            fontSize = 18.sp,
                        )
                    )
                    TextField(
                        value = recomendaciones,
                        onValueChange = { recomendaciones = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            val datosEvaluar = EvaluarTestRequest(GlobalState.id_usutip, comentarios, recomendaciones)
                            evaluarViewModel.editEvaluacionTest(diagnostico?.id_diag!!, datosEvaluar){
                                    validacion ->
                                if(evaluarViewModel.validacion_eval == "Evaluacion exitosa"){
                                    showPopupEva = true
                                }
                            }
                            estaEvaluado = true
                        }
                        ) {
                            Text(text = "Evaluar")
                        }
                        //Desabilitar el boton para evaluar
                        if (showPopupEva) {
                            AlertDialog(
                                onDismissRequest = { showPopupEva = false },
                                title = { Text(text = "EVALUACION COMPLETADA") },
                                text = { Text(text = "Se evaluó la solicitud del paciente de forma exitosa.") },
                                confirmButton = {
                                    Button(onClick = { showPopupEva = false }) {
                                        Text(text = "Continuar")
                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.width(32.dp))
                        Button(onClick = {
                            val correoPaciente = diagnostico?.usuario_test?.usuario_tipo?.usuario?.correoinstitucional
                            val datosNotificar = NotificarRequest(
                                GlobalState.username,
                                GlobalState.password,correoPaciente!!,comentarios,recomendaciones)
                            evaluarViewModel.postNotificarEval(datosNotificar){
                                    validacion ->
                                if(evaluarViewModel.validacion_noti == "Notificacion exitosa"){
                                    showPopupNoti = true
                                }
                            }
                        },
                            enabled = estaEvaluado
                        ) {
                            Text(text = "Notificar")
                        }
                        if (showPopupNoti) {
                            val correoPaciente = diagnostico?.usuario_test?.usuario_tipo?.usuario?.correoinstitucional
                            AlertDialog(
                                onDismissRequest = { showPopupNoti = false },
                                title = { Text(text = "NOTIFICACION ENVIADA") },
                                text = {
                                    Text(text = "${nombre}.")
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(text = "${correoPaciente}.")
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(text = "!!Gracias por cuidar de nuestros estudiantes!!.")
                                },
                                confirmButton = {
                                    Button(onClick = {
                                        showPopupNoti = false
                                        navController.navigateUp()
                                    }) {
                                        Text(text = "Continuar")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }else {
        // Muestra un indicador de progreso o un mensaje mientras se carga
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(androidx.compose.ui.graphics.Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator() // Indicador de carga
        }
    }
}

