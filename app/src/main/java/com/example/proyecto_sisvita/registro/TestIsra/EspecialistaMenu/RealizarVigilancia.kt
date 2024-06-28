package com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu

//import android.icu.text.SimpleDateFormat
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_sisvita.MyApp
import com.example.proyecto_sisvita.R
import com.example.proyecto_sisvita.data.model.Diagnostico
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import com.example.proyecto_sisvita.viewmodel.VigilanciaViewModel
import java.text.SimpleDateFormat

class RealizarVigilancia : ComponentActivity() {
    val viewModel by viewModels<VigilanciaViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                viewModel.realizarVigilancia()
                PendientesScreen(viewModel.diagnosticos,viewModel)
            }
        }
    }
}

@Composable
fun PendientesScreen(listaDiagnosticos: List<Diagnostico>, viewModel: VigilanciaViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pendientes") {
        composable("pendientes") {
            PendientesContent(listaDiagnosticos,viewModel,navController)
        }
        composable("menuRevisar") {
            MenuRevisar()
        }
        composable("diagnostico/{nombre}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            DiagnosticoScreen(nombre)
        }
        composable("verMapaCalor") {
            VerMapaCalor()
        }
    }
}

@Composable
fun PendientesContent(listaDiagnosticos: List<Diagnostico>, viewModel: VigilanciaViewModel,navController: NavHostController) {
    val context = LocalContext.current
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    /*val pacientes = listOf(
        PacienteP("Andres Arriel Paladino", "ISRA", dateFormat.parse("24/10/2024"),30, "Ansiedad marcada"),
        PacienteP("Jorge Manrico Condorcanqui", "HAS", dateFormat.parse("22/09/2024"),45, "Ansiedad moderada"),
        PacienteP("Carlos Perez", "ISRA", dateFormat.parse("30/08/2024"),50, "Ansiedad moderada"),
        PacienteP("Maria Lopez", "STAI", dateFormat.parse("24/07/2024"),10, "Prueba ansiedad"),
        PacienteP("Ana Gomez", "STAI", dateFormat.parse("02/10/2024"),45, "Ansiedad moderada"),
        PacienteP("Luis Rodriguez", "HAS", dateFormat.parse("03/11/2024"),30, "Ansiedad marcada"),
        PacienteP("Eduardo Armado", "ISRA", dateFormat.parse("23/09/2024"),10, "Prueba ansiedad"),
    )*/
    val itemsPerPage = 2
    val totalPages = (listaDiagnosticos.size + itemsPerPage - 1) / itemsPerPage
    val currentPage = remember { mutableStateOf(0) }
    println(listaDiagnosticos)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(40.dp)
                .size(80.dp)
        )
        Text(
            text = "Realizar Vigilancia",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF45ACCC)
        )
        Spacer(modifier = Modifier.height(16.dp))

        val startIndex = currentPage.value * itemsPerPage
        val endIndex = (startIndex + itemsPerPage).coerceAtMost(listaDiagnosticos.size)
        val currentItems = listaDiagnosticos.subList(startIndex, endIndex)

        LazyColumn {
            items(currentItems) { diagnostico ->
                PendienteCard(diagnostico) {
                    navController.navigate("diagnostico/${diagnostico.nombres}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        PaginationControls(currentPage.value, totalPages) { newPage ->
            currentPage.value = newPage
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                context.startActivity(Intent(context, MenuRevisar::class.java))
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFC3F52) // Rojo claro
            )) {
                Text("Atrás")
            }
            Button(
                onClick = {
                    context.startActivity(Intent(context, VerMapaCalor::class.java))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5722) // Rojo claro
                )
            ) {
                Text("Mapa de calor")
            }
        }
    }
}

@Composable
fun PaginationControls(currentPage: Int, totalPages: Int, onPageChange: (Int) -> Unit) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = { if (currentPage > 0) onPageChange(currentPage - 1) },
            enabled = currentPage > 0
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Anterior")
        }
        Text(
            text = "${currentPage + 1} / $totalPages",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        IconButton(
            onClick = { if (currentPage < totalPages - 1) onPageChange(currentPage + 1) },
            enabled = currentPage < totalPages - 1
        ) {
            Icon(Icons.Filled.ArrowForward, contentDescription = "Siguiente")
        }
    }
}

fun getColorForAnxietyLevel(nivel_ansiedad: Int): Color {
    return when (nivel_ansiedad) {
        1 -> Color(0xFF4CAF50) // Verde claro
        2-> Color(0xFFDCEDC1) // Verde limon
        3 -> Color(0xFFFFF176) // Amarillo
        4 -> Color(0xFFFFAB40) // Naranja
        5 -> Color(0xFFFF5252) // Rojo
        else -> Color(0xFF50ABCE) // Color por defecto
    }
}
fun AnsiedadTexto(nivel_ansiedad: Int): String{
    return when (nivel_ansiedad) {
        1 -> "Prueba Ansiedad" // Verde claro
        2-> "Ansiedad marcada"// Verde limon
        3 -> "Ansiedad moderada" // Amarillo
        4 -> "Ansiead severa" // Naranja
        5 -> "Ansiead extrema" // Rojo
        else -> "Sin nivel" // Color por defecto
    }
}
@Composable
fun PendienteCard(diagnostico: Diagnostico, navigateToDiagnostico: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = getColorForAnxietyLevel(diagnostico.id_nivel)),
        elevation = CardDefaults.elevatedCardElevation(20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .size(325.dp, 180.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("${diagnostico.nombres} ${diagnostico.apellidos}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Test: ${diagnostico.tipo_test}", fontWeight = FontWeight.Normal)
                Text("Fecha: ${diagnostico.fecha_test}", fontWeight = FontWeight.Normal)
                Text("Puntaje: ${diagnostico.puntaje}", fontWeight = FontWeight.Normal)
                Text("Nivel: ${AnsiedadTexto(diagnostico.id_nivel)}", fontWeight = FontWeight.Normal)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = navigateToDiagnostico,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF9400FF) )// Rojo claro
                ) {
                    Text("Evaluar")
                }
            }
            Image(
                painter = painterResource(id = R.drawable.especialista),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PendientesPreview() {
    val viewModel = VigilanciaViewModel().apply {
        realizarVigilancia()
    }
    ProyectoSISVITATheme {
        MyApp {
            viewModel.realizarVigilancia()
            PendientesContent(viewModel.diagnosticos,viewModel,rememberNavController())
        }
    }
}
