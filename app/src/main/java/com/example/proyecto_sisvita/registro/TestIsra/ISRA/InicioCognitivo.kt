package com.example.proyecto_sisvita.registro.TestIsra.ISRA

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.R
import com.example.proyecto_sisvita.TestISRA
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import com.example.proyecto_sisvita.viewmodel.PreguntasTestViewModel
import com.example.proyecto_sisvita.viewmodel.RespuestasTestViewModel
import kotlinx.coroutines.delay

class InicioCognitivo: ComponentActivity() {

val viewModelPre by viewModels<PreguntasTestViewModel>()
val viewModelRes by viewModels<RespuestasTestViewModel>()
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        ProyectoSISVITATheme{
            Surface (
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ){
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    viewModelPre.getPreguntas()
                    viewModelRes.getRespuestas()
                    MostrarPreguntasConRespuestas(viewModelPre._listaPreguntas,viewModelRes._listaRespuestas)
                }
            }
        }
    }
}
}
@Composable
fun MostrarPreguntasConRespuestas(
    listaPreguntas: List<ArrayList<String>>,
    listaRespuestas: List<ArrayList<String>>
) {
    val selectedAnswers = remember { mutableStateOf(mutableListOf<Pair<Int, Int>>()) }

    Column {
        LazyColumn {
            itemsIndexed(listaPreguntas) { indexPregunta, pregunta ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "${pregunta[1]}: ${pregunta[0]}")
                        // Verificar que existan respuestas asociadas a esta pregunta
                        if (indexPregunta < listaPreguntas.size) {
                            val respuestasPregunta = listaRespuestas
                            respuestasPregunta.forEachIndexed {indexRespuesta,respuesta ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(start = 16.dp)
                                ) {
                                    Checkbox(
                                        checked = selectedAnswers.value.any { it.first == pregunta[1].toInt() && it.second == respuesta[1].toInt() },
                                        onCheckedChange = { isChecked ->
                                            if (isChecked) {
                                                selectedAnswers.value.add(Pair(pregunta[1].toInt(), respuesta[1].toInt()))
                                            } else {
                                                selectedAnswers.value.remove(Pair(pregunta[1].toInt(), respuesta[1].toInt()))
                                            }
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Color.Green,
                                            uncheckedColor = Color.Black
                                        )
                                    )
                                    Text(text = " ${respuesta[1]} - ${respuesta[0]} ",
                                        modifier = Modifier.clickable {
                                            selectedAnswers.value.add(Pair(pregunta[1].toInt(), respuesta[1].toInt()))
                                        })
                                }
                            }
                        }
                    }
                }
            }
        }
            Button(onClick = {
                println("Selected answers: $selectedAnswers")
            }) {
                Text("Submit Answers")
            }
    }
}