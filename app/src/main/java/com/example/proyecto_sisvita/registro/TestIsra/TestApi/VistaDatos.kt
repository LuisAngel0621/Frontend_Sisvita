package com.example.proyecto_sisvita.registro.TestIsra.TestApi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_sisvita.MyApp
import com.example.proyecto_sisvita.data.model.Pregunta
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import com.example.proyecto_sisvita.viewmodel.QuestionViewModel

class VistaDatos: ComponentActivity(){

    val viewModel by viewModels<QuestionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ProyectoSISVITATheme{
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ){
                        viewModel.obtenerPreguntas()
                        VistaDatosScreen(viewModel.preguntas,viewModel)
                    }
                }
            }
        }

    }
}

@Composable
fun VistaDatosScreen(listaPreguntas: ArrayList<Pregunta>, viewModel: QuestionViewModel){
    var id by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        LazyColumn (
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ){
            items(listaPreguntas){ pregunta ->
                cartaPregunta(
                    pregunta = pregunta,
                    funIdPregunta = {id = it},
                    funTexto = {text = it}
                )
            }
        }
    }
}
@Composable
fun cartaPregunta(
    funIdPregunta: (String) -> Unit,
    funTexto: (String) -> Unit,
    pregunta: Pregunta
) {
    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            Arrangement.Center
        ){
            Text(
                text = "ID: ${pregunta.id_preguntas}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Texto: ${pregunta.descripcion}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable

fun DefaultPreview() {
    val viewModel = QuestionViewModel().apply {
        obtenerPreguntas()
    }
    ProyectoSISVITATheme {
        MyApp {
            viewModel.obtenerPreguntas()
            VistaDatosScreen(viewModel.preguntas,viewModel)
        }
    }
}
