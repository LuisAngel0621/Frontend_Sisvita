package com.example.proyecto_sisvita.registro.TestIsra.ISRA

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_sisvita.data.model.Answers
import com.example.proyecto_sisvita.data.model.Categoria
import com.example.proyecto_sisvita.data.model.Diagnostico
import com.example.proyecto_sisvita.data.model.Escala
import com.example.proyecto_sisvita.data.model.Nivel
import com.example.proyecto_sisvita.data.model.Paciente
import com.example.proyecto_sisvita.data.model.Pregunta
import com.example.proyecto_sisvita.data.model.Respuesta
import com.example.proyecto_sisvita.data.model.Test
import com.example.proyecto_sisvita.data.model.TipoTest
import com.example.proyecto_sisvita.data.model.UsuarioTest
import com.example.proyecto_sisvita.data.model.UsuarioTipo
import com.example.proyecto_sisvita.network.GlobalState
import com.example.proyecto_sisvita.registro.TestIsra.Login.MainScreen
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import com.example.proyecto_sisvita.viewmodel.LoginViewModel
import com.example.proyecto_sisvita.viewmodel.PreguntasTestViewModel
import com.example.proyecto_sisvita.viewmodel.RespuestasTestViewModel
import com.example.proyecto_sisvita.viewmodel.TestViewModel
import kotlinx.coroutines.delay

class InicioCognitivo: ComponentActivity() {

val viewModelPre by viewModels<PreguntasTestViewModel>()
val viewModelRes by viewModels<RespuestasTestViewModel>()
val viewModel by viewModels<TestViewModel>()
val viewModelLogin by viewModels<LoginViewModel>()
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
                    viewModelRes.getEscala()
                    AppNavigation(
                        listapreguntas = viewModelPre.listapreg,
                        listarespuestas = viewModelRes.listaresp,
                        listaescala = viewModelRes.listaEscala,
                        viewModel = viewModel,
                        viewModelLogin = viewModelLogin
                    )
                }
            }
        }
    }
}
}
@Composable
fun AppNavigation(
    listapreguntas: List<Pregunta>,
    listarespuestas: List<Respuesta>,
    listaescala: List<Escala>,
    viewModel: TestViewModel,
    viewModelLogin: LoginViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MostrarPreguntasConRespuestas(
            listapreguntas = listapreguntas,
            listarespuestas = listarespuestas,
            listaescala = listaescala,
            viewModel = viewModel,
            navController
        ) }
        composable("fisiologico") {Mostrar_test_Fisiologico(
            listapreguntas = listapreguntas,
            listarespuestas = listarespuestas,
            listaescala = listaescala,
            viewModel = viewModel,
            navController
        ) }
        composable("motor"){Mostrar_test_Motor(
            listapreguntas = listapreguntas,
            listarespuestas = listarespuestas,
            listaescala = listaescala,
            viewModel = viewModel,
            navController
        )
        }
        composable("inicio"){
            MainScreen(viewModel = viewModelLogin )
        }
    }
}

@Composable
fun MostrarPreguntasConRespuestas(
    listapreguntas: List<Pregunta>,
    listarespuestas: List<Respuesta>,
    listaescala: List<Escala>,
    viewModel: TestViewModel,
    navController: NavHostController
) {
    val selectedanwers = remember {
        mutableStateListOf<Answers>()
    }
    val showDialog = remember {
        mutableStateOf(false)
    }
    // Función para mostrar el diálogo
    fun showAlertDialog() {
        showDialog.value = true
    }

    // Función para cerrar el diálogo
    fun dismissDialog() {
        showDialog.value = false
    }
    //Codigo Anterior
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "TEST ISRA",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Categoría 1: COGNITIVO",
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 16.dp)) {
                println("Id_tipo")
                println(GlobalState.id_tipo)
                println("Holaaa")
                println(listapreguntas)

                itemsIndexed(listapreguntas) { indexPregunta, pregunta ->
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            println(pregunta.descripcion)
                            Text(text = "${pregunta.descripcion}: ${pregunta.id_preguntas}")
                            // Verificar que existan respuestas asociadas a esta pregunta

                            if (indexPregunta < listapreguntas.size) {
                                val respuestasPregunta = listarespuestas
                                respuestasPregunta.forEachIndexed { indexRespuesta, respuesta ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(start = 16.dp)
                                    ) {
                                        Checkbox(
                                            checked = selectedanwers.any { answers ->
                                                answers.id_preguntas == pregunta.id_preguntas && answers.id_respuestas == respuesta.id_respuestas },
                                            onCheckedChange = { isChecked ->
                                                if (isChecked) {
                                                    selectedanwers.add(Answers(0,respuesta.id_respuestas,pregunta.id_preguntas))


                                                } else {
                                                    selectedanwers.removeIf { answers ->
                                                        answers.id_preguntas== pregunta.id_preguntas && answers.id_respuestas == respuesta.id_respuestas }

                                                }
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = Color.Green,
                                                uncheckedColor = Color.Black
                                            )
                                        )
                                        Text(
                                            text = "${respuesta.descripcion} ",
                                            modifier = Modifier.clickable {
                                                if (!selectedanwers.contains(
                                                        Answers(0,respuesta.id_respuestas,pregunta.id_preguntas)

                                                    )
                                                ) {
                                                    selectedanwers.add(
                                                        Answers(0,respuesta.id_respuestas,pregunta.id_preguntas)

                                                    )
                                                }
                                            })
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                ScaleSelector(
                                    questionId = pregunta.id_preguntas,
                                    selectedanwers = selectedanwers,
                                    listaescala = listaescala
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                //println("Selected answers: ${selectedanwers.toList()}")
                //println(UsuarioTest(1,81,selectedanwers.toList()))
                viewModel.addTest(
                    UsuarioTest(
                        id_test = 1,
                        id_paciente = GlobalState.id_tipo,
                        answers = selectedanwers.toList(),
                        test = Test(id_categoria=Categoria(),tipo_test= TipoTest()),
                        usuario_tipo = UsuarioTipo(usuario= Paciente())
                    )
                ){
                        id_user_test ->
                    navController.navigate("fisiologico")
                    println("Botón Siguiente presionado")

                }
                println("----Suma de Escala")
                showAlertDialog()

            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Siguiente")
        }

    }
}


@Composable
fun Mostrar_test_Fisiologico(
    listapreguntas: List<Pregunta>,
    listarespuestas: List<Respuesta>,
    listaescala: List<Escala>,
    viewModel: TestViewModel,
    navController: NavHostController
) {
    val selectedanwers = remember {
        mutableStateListOf<Answers>()
    }
    val showDialog = remember {
        mutableStateOf(false)
    }
    // Función para mostrar el diálogo
    fun showAlertDialog() {
        showDialog.value = true
    }

    //Codigo Anterior
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "TEST ISRA",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Categoría 2: FISIOLOGICO",
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            LazyColumn(modifier = Modifier.padding(bottom = 100.dp)) {
                println("Id_tipo")
                println(GlobalState.id_tipo)
                println("Holaaa")
                println(listapreguntas)

                itemsIndexed(listapreguntas) { indexPregunta, pregunta ->
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            println(pregunta.descripcion)
                            Text(text = "${pregunta.descripcion}: ${pregunta.id_preguntas}")
                            // Verificar que existan respuestas asociadas a esta pregunta

                            if (indexPregunta < listapreguntas.size) {
                                val respuestasPregunta = listarespuestas
                                respuestasPregunta.forEachIndexed { indexRespuesta, respuesta ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(start = 16.dp)
                                    ) {
                                        Checkbox(
                                            checked = selectedanwers.any { answers ->
                                                answers.id_preguntas == pregunta.id_preguntas && answers.id_respuestas == respuesta.id_respuestas },
                                            onCheckedChange = { isChecked ->
                                                if (isChecked) {
                                                    selectedanwers.add(Answers(0,respuesta.id_respuestas,pregunta.id_preguntas))

                                                } else {
                                                    selectedanwers.removeIf { answers ->
                                                        answers.id_preguntas== pregunta.id_preguntas && answers.id_respuestas == respuesta.id_respuestas }

                                                }
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = Color.Green,
                                                uncheckedColor = Color.Black
                                            )
                                        )
                                        Text(
                                            text = "${respuesta.descripcion} ",
                                            modifier = Modifier.clickable {
                                                if (!selectedanwers.contains(
                                                        Answers(0,respuesta.id_respuestas,pregunta.id_preguntas)

                                                    )
                                                ) {
                                                    selectedanwers.add(Answers(0,respuesta.id_respuestas,pregunta.id_preguntas)

                                                    )
                                                }
                                            })
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                ScaleSelector(
                                    questionId = pregunta.id_preguntas,
                                    selectedanwers = selectedanwers,
                                    listaescala = listaescala
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.addTest(
                    UsuarioTest(
                        id_test = 2,
                        id_paciente = GlobalState.id_tipo,
                        answers = selectedanwers.toList(),
                        test = Test(id_categoria=Categoria(),tipo_test= TipoTest()),
                        usuario_tipo = UsuarioTipo(usuario= Paciente())
                    )
                ){
                        id_user_test ->
                    navController.navigate("motor")
                    println("Botón Siguiente presionado")
                }
                println("----Suma de Escala")
                showAlertDialog()

            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Siguiente")
        }



    }
}

@Composable
fun Mostrar_test_Motor(
    listapreguntas: List<Pregunta>,
    listarespuestas: List<Respuesta>,
    listaescala: List<Escala>,
    viewModel: TestViewModel,
    navController: NavHostController
){
    val selectedanwers = remember {
        mutableStateListOf<Answers>()
    }
    var showDialog by remember { mutableStateOf(false) }

    //Codigo Anterior
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "TEST ISRA",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Categoría 3: MOTOR",
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            LazyColumn(modifier = Modifier.padding(bottom = 100.dp)) {
                println("Id_tipo")
                println(GlobalState.id_tipo)
                println("Holaaa")
                println(listapreguntas)

                itemsIndexed(listapreguntas) { indexPregunta, pregunta ->
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            println(pregunta.descripcion)
                            Text(text = "${pregunta.descripcion}: ${pregunta.id_preguntas}")
                            // Verificar que existan respuestas asociadas a esta pregunta

                            if (indexPregunta < listapreguntas.size) {
                                val respuestasPregunta = listarespuestas
                                respuestasPregunta.forEachIndexed { indexRespuesta, respuesta ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(start = 16.dp)
                                    ) {
                                        Checkbox(
                                            checked = selectedanwers.any { answers ->
                                                answers.id_preguntas == pregunta.id_preguntas && answers.id_respuestas == respuesta.id_respuestas },
                                            onCheckedChange = { isChecked ->
                                                if (isChecked) {
                                                    selectedanwers.add(Answers(0,respuesta.id_respuestas,pregunta.id_preguntas))

                                                } else {
                                                    selectedanwers.removeIf { answers ->
                                                        answers.id_preguntas== pregunta.id_preguntas && answers.id_respuestas == respuesta.id_respuestas }

                                                }
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = Color.Green,
                                                uncheckedColor = Color.Black
                                            )
                                        )
                                        Text(
                                            text = "${respuesta.descripcion} ",
                                            modifier = Modifier.clickable {
                                                if (!selectedanwers.contains(
                                                        Answers(0,respuesta.id_respuestas,pregunta.id_preguntas)

                                                    )
                                                ) {
                                                    selectedanwers.add(Answers(0,respuesta.id_respuestas,pregunta.id_preguntas)

                                                    )
                                                }
                                            })
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                ScaleSelector(
                                    questionId = pregunta.id_preguntas,
                                    selectedanwers = selectedanwers,
                                    listaescala = listaescala
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                viewModel.addTest(
                    UsuarioTest(
                        id_test = 3,
                        id_paciente = GlobalState.id_tipo,
                        answers = selectedanwers.toList(),
                        test = Test(id_categoria=Categoria(),tipo_test= TipoTest()),
                        usuario_tipo = UsuarioTipo(usuario= Paciente())
                    )
                ){
                        id_user_test ->

                    viewModel.sumEscala(GlobalState.id_tipo){
                            puntaje ->
                        GlobalState.puntaje = puntaje
                        println(puntaje)
                        viewModel.getNivel {
                                lista ->
                            val nivelAnsiedad = lista.find { nivel ->
                                puntaje in nivel.rang_min..nivel.rang_max
                            }
                            if(nivelAnsiedad != null){
                                GlobalState.nivel_ansiedad = nivelAnsiedad.nivel_ansiedad
                                viewModel.resultadoTest(
                                    Diagnostico(
                                        id_user_test = id_user_test,
                                        id_nivel = nivelAnsiedad.id_nivel,
                                        puntaje = puntaje,
                                        comentario = "",
                                        recomendacion = "",
                                        tipo_nivel = Nivel(),
                                        usuario_test = UsuarioTest(test = Test(id_categoria=Categoria(),tipo_test= TipoTest()),usuario_tipo = UsuarioTipo(usuario= Paciente()))
                                    )
                                ){
                                        id_diag ->
                                    GlobalState.id_diag = id_diag
                                    showDialog = true  // Mostrar cuadro de diálogo
                                }
                            }
                            else{
                                println("El puntaje no se encuentra dentro de ningún rango de nivel de ansiedad")
                            }
                        }
                    }

                }
                println("----Suma de Escala")


            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Enviar Respuestas")
        }
        //Cuadro de dialogo
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Test realizado !!") },
                text = {
                    Column {
                        Text("Resultados obtenidos:")
                        Text("Puntaje: ${GlobalState.puntaje}")
                        Text("Nivel de ansiedad: ${GlobalState.nivel_ansiedad}")
                        Text("Su diagnostico sera enviado al correo..")
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        navController.navigate("inicio")
                        println("Botón Siguiente presionado")
                    }) {
                        Text("Aceptar")
                    }
                }

            )
        }



    }

}


@Composable
fun ScaleSelector(
    questionId: Int,
    selectedanwers: MutableList<Answers>,
    listaescala: List<Escala>
) {
    val initialScale = selectedanwers.find { answers ->
        answers.id_preguntas == questionId }?.id_escala ?: 0
    val selectedScale = remember { mutableStateOf(initialScale) }

    Column(modifier = Modifier.padding(5.dp)) {
        Text(text = "Selecciona una escala de 1 a 5:")
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            listaescala.forEachIndexed{indexEscala, escala ->

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "${escala.id_escala}",
                        modifier = Modifier.width(10.dp)
                    )
                    RadioButton(
                        selected = (selectedScale.value == escala.id_escala),
                        onClick = {
                            selectedScale.value = escala.id_escala
                            val currentAnswer = selectedanwers.find {answers ->
                                answers.id_preguntas == questionId }
                            if (currentAnswer != null) {
                                selectedanwers[selectedanwers.indexOf(currentAnswer)] =
                                    Answers(escala.id_escala,currentAnswer.id_respuestas,currentAnswer.id_preguntas)

                            } else {
                                selectedanwers.add(
                                    Answers(0,questionId,escala.id_escala))

                            }
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Green,
                            unselectedColor = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                }
            }
        }

    }
}