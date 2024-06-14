@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.proyecto_sisvita.registro.TestIsra.ISRA

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class TestISRA : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                testIsra()
            }
        }
    }
}

data class Question(
    val text: String,
    val options: List<String> = listOf(
        "Me preocupo fácilmente",//1
        "Tengo pensamientos o pensamientos negativos sobre mi: inferioridad o torpeza",//2
        "Me siento inseguro de mi mismo",//3
        "Doy demasiadas vueltas a las cosas sin llegar a decidirme",//4
        "Siento miedo",//5
        "Me cuesta concentrarme",//6
        "Pienso que la gente se dará cuenta de mis problemas o de la torpeza de mis actos"//7
    )
)

class QuestionViewModel : ViewModel() {
    val answers = mutableStateListOf<Answer>()
}
data class Answer(
    val question: String,
    var selectedOption: Int = -1,
    var selectedScale: Int = -1
)

val questions  = listOf(
    Question("¿Ante un examen importante o una entrevista de trabajo?"),
    Question("¿Cuando voy a llegar tarde a una cita?"),
    Question("¿Cuando pienso en todas las cosas que tengo que hacer?"),
    Question("¿Al momento de tomar una decisión o resolver un problema difícil?"),
    Question("¿En mi trabajo o cuando estudio?"),
    Question("¿Cuando espero a alguien en un lugar concurrido?"),
    Question("¿Si una persona está muy cerca mío o en una situación sexual íntima?"),
    Question("¿Cuando alguien me molesta o cuando discuto?"),
    Question("¿Cuando recibo algún tipo de observación/crítica/evaluación sobre mi trabajo?"),
    Question("¿Si tengo que hablar en público?"),
    Question("¿Cuando recuerdo situaciones en donde me sentí humillado, tímido o rechazado?"),
    Question("¿Cuando tengo que viajar en avión o en barco?"),
    Question("¿Después de haber cometido algún error?"),
    Question("¿Ante la consulta del dentista, inyecciones, heridas o ver sangre?"),
    Question("¿Cuando voy a una cita con una persona del otro sexo?"),
    Question("¿Cuando pienso en mi futuro o en dificultades y problemas futuros?"),
    Question("¿En medio de multitudes o espacios cerrados?"),
    Question("¿Cuando tengo que asistir a una reunión social o conocer gente nueva?"),
    Question("¿En lugares altos o ante aguas profundas?"),
    Question("¿Al observar escenas violentas?"),
    Question("¿Por nada en concreto?"),
    Question("¿A la hora de dormir?"),
)

val answers = mutableStateListOf<Answer>()


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun testIsra(viewModel: QuestionViewModel = viewModel()) {
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
                            QuestionItem(question,viewModel)
                            Divider()
                        }
                    }

                    Button(
                        onClick = { context.startActivity(Intent(context, finISRA::class.java)) },
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
fun QuestionItem(question: Question, viewModel: QuestionViewModel) {
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
fun updateAnswer(question: String, selectedOption: Int, selectedScale: Int, viewModel: QuestionViewModel) {
    val answer = Answer(question, selectedOption, selectedScale)
    viewModel.answers.removeAll { it.question == question }
    viewModel.answers.add(answer)
}

@Preview(showBackground = true)
@Composable
fun testIsraPreview() {
    ProyectoSISVITATheme {
        MyApp {
            testIsra()
        }
    }
}