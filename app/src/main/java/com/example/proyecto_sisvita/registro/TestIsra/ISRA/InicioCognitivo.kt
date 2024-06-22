package com.example.proyecto_sisvita.registro.TestIsra.ISRA

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
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
    val selectedAnswers = remember { mutableStateListOf<Triple<Int, Int, Int>>()}

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(bottom = 100.dp)) {
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
                            respuestasPregunta.forEachIndexed { indexRespuesta, respuesta ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(start = 16.dp)
                                ) {
                                    Checkbox(
                                        checked = selectedAnswers.any { it.first == pregunta[1].toInt() && it.second == respuesta[1].toInt() },
                                        onCheckedChange = { isChecked ->
                                            if (isChecked) {
                                                selectedAnswers.add(Triple(pregunta[1].toInt(), respuesta[1].toInt(), 0))
                                            } else {
                                                selectedAnswers.removeIf { it.first == pregunta[1].toInt() && it.second == respuesta[1].toInt() }
                                            }
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Color.Green,
                                            uncheckedColor = Color.Black
                                        )
                                    )
                                    Text(
                                        text = "${respuesta[0]} ",
                                        modifier = Modifier.clickable {
                                            if (!selectedAnswers.contains(Triple(pregunta[1].toInt(), respuesta[1].toInt(), 0))) {
                                                selectedAnswers.add(Triple(pregunta[1].toInt(), respuesta[1].toInt(), 0))
                                            }
                                        })
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            ScaleSelector(
                                questionId = pregunta[1].toInt(),
                                selectedAnswers = selectedAnswers
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                println("Selected answers: ${selectedAnswers.toList()}")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Enviar Respuestas")
        }
    }
}

@Composable
fun ScaleSelector(
    questionId: Int,
    selectedAnswers: MutableList<Triple<Int, Int, Int>>
) {
    val initialScale = selectedAnswers.find { it.first == questionId }?.third ?: 0
    val selectedScale = remember { mutableStateOf(initialScale) }

    Column(modifier = Modifier.padding(5.dp)) {
        Text(text = "Selecciona una escala de 1 a 5:")
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            for (i in 1..5) {
                Text(text = "$i",
                    modifier = Modifier.width(10.dp)
                )
                RadioButton(
                    selected = (selectedScale.value == i),
                    onClick = {
                        selectedScale.value = i
                        val currentAnswer = selectedAnswers.find { it.first == questionId }
                        if (currentAnswer != null) {
                            selectedAnswers[selectedAnswers.indexOf(currentAnswer)] = Triple(currentAnswer.first, currentAnswer.second, i)
                        } else {
                            selectedAnswers.add(Triple(questionId, 0, i))
                        }
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Green,
                        unselectedColor = Color.Black
                    )
                )
            }
            Spacer(modifier = Modifier.width(1.dp))
        }
    }
}