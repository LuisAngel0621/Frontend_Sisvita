@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.proyecto_sisvita.registro.TestIsra.ISRA

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.proyecto_sisvita.MyApp
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_sisvita.data.model.Preguntas
import com.example.proyecto_sisvita.data.model.Respuestas
import com.example.proyecto_sisvita.data.model.UsuarioTest
import com.example.proyecto_sisvita.viewmodel.PacientesViewModel
import com.example.proyecto_sisvita.viewmodel.QuestionViewModel
import com.example.proyecto_sisvita.viewmodel.TestViewModel

class TestISRA : ComponentActivity() {

    val viewModel_question by viewModels<QuestionViewModel>()
    val viewModel_test by viewModels<TestViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                testIsra(viewModel_question = viewModel_question,viewModel_test = viewModel_test)
            }
        }
    }
}





val questions  = listOf(
    Preguntas("¿Ante un examen importante o una entrevista de trabajo?"),
    Preguntas("¿Cuando voy a llegar tarde a una cita?"),
    Preguntas("¿Cuando pienso en todas las cosas que tengo que hacer?"),
    Preguntas("¿Al momento de tomar una decisión o resolver un problema difícil?"),
    Preguntas("¿En mi trabajo o cuando estudio?"),
    Preguntas("¿Cuando espero a alguien en un lugar concurrido?"),
    Preguntas("¿Si una persona está muy cerca mío o en una situación sexual íntima?"),
    Preguntas("¿Cuando alguien me molesta o cuando discuto?"),
    Preguntas("¿Cuando recibo algún tipo de observación/crítica/evaluación sobre mi trabajo?"),
    Preguntas("¿Si tengo que hablar en público?"),
    Preguntas("¿Cuando recuerdo situaciones en donde me sentí humillado, tímido o rechazado?"),
    Preguntas("¿Cuando tengo que viajar en avión o en barco?"),
    Preguntas("¿Después de haber cometido algún error?"),
    Preguntas("¿Ante la consulta del dentista, inyecciones, heridas o ver sangre?"),
    Preguntas("¿Cuando voy a una cita con una persona del otro sexo?"),
    Preguntas("¿Cuando pienso en mi futuro o en dificultades y problemas futuros?"),
    Preguntas("¿En medio de multitudes o espacios cerrados?"),
    Preguntas("¿Cuando tengo que asistir a una reunión social o conocer gente nueva?"),
    Preguntas("¿En lugares altos o ante aguas profundas?"),
    Preguntas("¿Al observar escenas violentas?"),
    Preguntas("¿Por nada en concreto?"),
    Preguntas("¿A la hora de dormir?"),
)

val answers = mutableStateListOf<Preguntas>()


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun testIsra(viewModel_question: QuestionViewModel ,viewModel_test: TestViewModel ) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TEST DE ANSIEDAD",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFB3E5FC))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(50.dp))

                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(questions) { question ->
                            QuestionItem(question,viewModel_question)
                            Divider()
                        }
                    }

                    Button(
                        onClick = {

                            context.startActivity(Intent(context, finISRA::class.java)) },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Enviar respuestas")
                    }
                }
            }
        }
    )
}

@Composable
fun QuestionItem(question: Preguntas, viewModel: QuestionViewModel) {
    var selectedOption by rememberSaveable { mutableStateOf(-1) }
    var selectedScale by rememberSaveable { mutableStateOf(-1) }

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = question.text,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(modifier = Modifier.padding(start = 16.dp)) {
            question.options.forEachIndexed { index, option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = selectedOption == index,
                        onClick = {
                            selectedOption = index
                            updateAnswer(question.text, selectedOption, selectedScale, viewModel)
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = option)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Escala del 1 al 5:")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            (1..5).forEach { scale ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedScale == scale,
                        onClick = {
                            selectedScale = scale
                            updateAnswer(question.text, selectedOption, selectedScale, viewModel)
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = scale.toString())
                }
            }
        }
    }
}
fun updateAnswer(question: String, selectedOption: Int, selectedScale: Int, viewModel_question: QuestionViewModel) {
    val answer = Respuestas(question, selectedOption, selectedScale)
    viewModel_question.answers.removeAll { it.question == question }
    viewModel_question.answers.add(answer)
}

