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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun PendientesScreen(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "pendientes") {
        composable("pendientes") { PendientesContent(navController) }
        composable("diagnostico") { /* Aquí iría la navegación a la pantalla de diagnóstico */ }
    }
}

@Composable
fun PendientesContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFE0F7FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Text(onTextLayout = {},
            text = "Revisiones Pendientes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        PaginacionRevisiones(navController)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigateUp() },
            shape = RoundedCornerShape(50)
        ) {
            Text("Volver",onTextLayout = {})
        }
    }
}

@Composable
fun PaginacionRevisiones(navController: NavHostController) {
    // Aquí se muestran las revisiones pendientes
    val pacientes = listOf(
        Paciente("Andres Arriel Paladino", 28, 30, 12, "ALTO"),
        Paciente("Jorge Manrico Condorcanqui", 10, 7, 5, "MEDIO")
    )

    Column {
        pacientes.chunked(2).forEachIndexed { index, chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                chunk.forEach { paciente ->
                    RevisionCard(navController, paciente)
                }
            }
        }
    }
}

@Composable
fun RevisionCard(navController: NavHostController, paciente: Paciente) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.45f)
            .background(Color.White, RoundedCornerShape(8.dp)),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(paciente.nombre, fontWeight = FontWeight.Bold,onTextLayout = {})
            Spacer(modifier = Modifier.height(8.dp))
            Text("Cognitivo: ${paciente.cognitivo}", color = Color.Red,onTextLayout = {})
            Text("Fisiologico: ${paciente.fisiologico}", color = Color.Red,onTextLayout = {})
            Text("Motor: ${paciente.motor}", color = Color.Red,onTextLayout = {})
            Text("Resultado General: ${paciente.resultadoGeneral}", color = Color.Red,onTextLayout = {})
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.especialista),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("diagnostico") },
                shape = RoundedCornerShape(50)
            ) {
                Text("Revisar",onTextLayout = {})
            }
        }
    }
}

data class Paciente(
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
            PendientesScreen()
        }
    }
}


