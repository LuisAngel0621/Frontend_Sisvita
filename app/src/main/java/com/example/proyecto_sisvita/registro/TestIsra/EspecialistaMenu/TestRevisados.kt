package com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

class TestRevisados : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                RevisadosScreen()
            }
        }
    }
}

@Composable
fun RevisadosScreen(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "revisados") {
        composable("revisados") { RevisadosContent(navController) }
    }
}

@Composable
fun RevisadosContent(navController: NavHostController) {
    val context = LocalContext.current
    val pacientes = listOf(
        PacienteR("Andres Arriel Paladino", "ALTO", "EN REVISION"),
        PacienteR("Jorge Manrico Condorcanqui",  "MEDIO","ATENDIDO"),
        PacienteR("Carlos Perez", "ALTO","ATENDIDO"),
        PacienteR("Maria Lopez", "BAJO","EN REVISION"),
        PacienteR("Ana Gomez", "MEDIO","EN REVISION"),
        PacienteR("Luis Rodriguez",  "ALTO","ATENDIDO")
    )

    val itemsPerPage = 4
    val totalPages = (pacientes.size + itemsPerPage - 1) / itemsPerPage
    val currentPage = remember { mutableStateOf(0) }
    val estados = listOf("En revisión", "Atendido")
    val estadoSeleccionado = remember { mutableStateOf(estados[0]) }


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
                .padding(20.dp)
                .size(40.dp)
        )
        Text(
            text = "Seguimiento de pacientes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF45ACCC)
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomDropdownMenuRevisado(
            value = estadoSeleccionado.value,
            onValueChange = { estadoSeleccionado.value = it },
            label = "Estado",
            options = estados
        )
        Spacer(modifier = Modifier.height(8.dp))
        val startIndex = currentPage.value * itemsPerPage
        val endIndex = (startIndex + itemsPerPage).coerceAtMost(pacientes.size)
        val currentItems = pacientes.subList(startIndex, endIndex)
        LazyColumn {
            items(currentItems) { paciente ->
                RevisionCard(paciente,navController)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        PaginationControlsR(currentPage.value, totalPages) { newPage ->
            currentPage.value = newPage
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = { (context as? MenuRevisar)?.finish() },
            shape = RoundedCornerShape(25),
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun PaginationControlsR(currentPage: Int, totalPages: Int, onPageChange: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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

// Definición del RevisionCard que acepta paciente y navController
@Composable
fun RevisionCard(paciente: PacienteR, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF50ABCE)),
        elevation = CardDefaults.elevatedCardElevation(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).size(321.dp,80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(paciente.nombre, fontWeight = FontWeight.Bold, onTextLayout = {})
                Spacer(modifier = Modifier.height(4.dp))
                Text("Resultado General: ${paciente.resultadoGeneral}", color = Color(0xFF14504E), onTextLayout = {})
                Text("Estado: ${paciente.estado}", color = Color(0xFF14504E), onTextLayout = {})
                Spacer(modifier = Modifier.height(4.dp))
            }
            Button(
                onClick = { navController.navigate("diagnostico") },
                shape = RoundedCornerShape(40)
            ) {
                Text("Revisar", fontSize = 12.sp )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenuRevisado(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = { },
                label = { Text(label) },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledBorderColor = Color.Gray,
                    disabledTextColor = Color.DarkGray,
                    disabledLabelColor = Color.DarkGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                enabled = false // Disable to prevent focus
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

// Definición del modelo Paciente
data class PacienteR(
    val nombre: String,
    val resultadoGeneral: String,
    val estado: String
)
@Preview(showBackground = true)
@Composable
fun RevisadosPreview() {
    ProyectoSISVITATheme {
        MyApp {
            RevisadosScreen()
        }
    }
}


