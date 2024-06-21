@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.proyecto_sisvita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import com.example.proyecto_sisvita.viewmodel.PreguntasTestViewModel
import com.example.proyecto_sisvita.viewmodel.RespuestasTestViewModel

class TestISRA : ComponentActivity() {
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
                                    }
                                )
                                Text(text = " ${respuesta[1]}) ${respuesta[0]}")
                            }
                            Text(text = " ${respuesta[1]}) ${respuesta[0]} ",
                                modifier = Modifier.clickable {
                                    selectedAnswers.value.add(Pair(pregunta[1].toInt(), respuesta[1].toInt()))
                                })
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
}



