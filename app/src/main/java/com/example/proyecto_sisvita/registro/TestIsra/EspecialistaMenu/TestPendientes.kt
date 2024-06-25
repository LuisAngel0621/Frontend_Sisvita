package com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme

class TestPendientes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                PendientesScreen()
            }
        }
    }
}

@Composable
fun PendientesScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pendientes") {
        composable("pendientes") { PendientesContent(navController) }
        composable(
            "diagnostico/{nombre}"
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            DiagnosticoScreen(nombre)
        }
    }
}

@Composable
fun PendientesContent(navController: NavHostController) {
    val context = LocalContext.current
    val pacientes = listOf(
        PacienteP("Andres Arriel Paladino", 28, 30, 12, "ALTO"),
        PacienteP("Jorge Manrico Condorcanqui", 27, 25, 15, "MEDIO"),
        PacienteP("Carlos Perez", 30, 28, 20, "ALTO"),
        PacienteP("Maria Lopez", 25, 22, 18, "BAJO"),
        PacienteP("Ana Gomez", 24, 20, 10, "MEDIO"),
        PacienteP("Luis Rodriguez", 29, 26, 14, "ALTO")
    )

    val itemsPerPage = 2
    val totalPages = (pacientes.size + itemsPerPage - 1) / itemsPerPage
    val currentPage = remember { mutableStateOf(0) }

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
            text = "Revisiones Pendientes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF45ACCC)
        )
        Spacer(modifier = Modifier.height(16.dp))

        val startIndex = currentPage.value * itemsPerPage
        val endIndex = (startIndex + itemsPerPage).coerceAtMost(pacientes.size)
        val currentItems = pacientes.subList(startIndex, endIndex)

        LazyColumn {
            items(currentItems) { paciente ->
                PendienteCard(paciente) {
                    navController.navigate("diagnostico/${paciente.nombre}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        PaginationControls(currentPage.value, totalPages) { newPage ->
            currentPage.value = newPage
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { (context as? MenuRevisar)?.finish() },
            shape = RoundedCornerShape(50)
        ) {
            Text("Volver")
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

@Composable
fun PendienteCard(paciente: PacienteP, navigateToDiagnostico: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF50ABCE)),
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
                Text(paciente.nombre, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Cognitivo: ${paciente.cognitivo}", fontWeight = FontWeight.Normal)
                Text("Fisiológico: ${paciente.fisiologico}", fontWeight = FontWeight.Normal)
                Text("Motor: ${paciente.motor}", fontWeight = FontWeight.Normal)
                Text("Resultado General: ${paciente.resultadoGeneral}", fontWeight = FontWeight.Normal)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = navigateToDiagnostico,
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Revisar")
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

// Definición del modelo Paciente
data class PacienteP(
    val nombre: String,
    val cognitivo: Int,
    val fisiologico: Int,
    val motor: Int,
    val resultadoGeneral: String
)

@Preview(showBackground = true)
@Composable
fun PendientesPreview() {
    ProyectoSISVITATheme {
        MyApp {
            PendientesContent(rememberNavController())
        }
    }
}
