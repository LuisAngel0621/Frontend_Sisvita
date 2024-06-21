//"TestDiagnostico.kt"
/*package com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.example.proyecto_sisvita.MyApp
import com.example.proyecto_sisvita.R
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import java.util.Date

class TestDiagnostico : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var nombre = intent.getStringExtra("nombre") ?: ""
        val cognitivo = intent.getIntExtra("cognitivo", 0)
        val fisiologico = intent.getIntExtra("fisiologico", 0)
        val motor = intent.getIntExtra("motor", 0)
        val resultadoGeneral = intent.getStringExtra("resultadoGeneral") ?: ""
        setContent {
            MyApp {
                DiagnosticoScreen(
                    nombre = nombre,
                    cognitivo = cognitivo,
                    fisiologico = fisiologico,
                    motor = motor,
                    resultadoGeneral = resultadoGeneral
                )
            }
        }
    }
}

@Composable
fun DiagnosticoScreen(
                       nombre: String,
                       cognitivo: Int,
                       fisiologico: Int,
                       motor: Int,
                       resultadoGeneral: String
    ) {
    val motivos = listOf("Ansiedad Severa", "Depresión", "Estrés", "Otro")
    val motivoSeleccionado = remember { mutableStateOf(motivos[0]) }
    val diagnostico = remember { mutableStateOf("") }
    val fecha = remember { mutableStateOf(Date().toString()) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Text(onTextLayout = {},
            text = "Enviar Diagnóstico",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(20.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nombre: $nombre", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Cognitivo: $cognitivo", color = Color(0xFF14504E), fontSize = 18.sp)
                Text("Fisiológico: $fisiologico", color = Color(0xFF14504E), fontSize = 18.sp)
                Text("Motor: $motor", color = Color(0xFF14504E), fontSize = 18.sp)
                Text("Resultado General: $resultadoGeneral", color = Color(0xFF14504E), fontSize = 18.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { (context as? ComponentActivity)?.finish() },
                shape = RoundedCornerShape(50)
            ) {
                Text("Volver",onTextLayout = {})

            }
            Button(
                onClick = { /* Acción para enviar diagnóstico */ },
                shape = RoundedCornerShape(50)
            ) {
                Text("Enviar",onTextLayout = {})
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(
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
                label = { Text(label,onTextLayout = {}) },
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
                        text = { Text(option,onTextLayout = {}) },
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

@Preview(showBackground = true)
@Composable
fun DiagnosticoPreview() {
    ProyectoSISVITATheme {
        MyApp {
            DiagnosticoScreen(
                nombre = "Andres Arriel Paladino",
                cognitivo = 28,
                fisiologico = 30,
                motor = 12,
                resultadoGeneral = "ALTO"
            )
        }
    }
}*/

package com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.MyApp
import com.example.proyecto_sisvita.R
import com.example.proyecto_sisvita.registro.TestIsra.RegistroYPrimerTest.CustomTextField
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import java.util.Date

class TestDiagnostico : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                DiagnosticoScreen(
                    nombre = intent.getStringExtra("nombre") ?: ""
                )
            }
        }
    }
}

@Composable
fun DiagnosticoScreen(nombre: String) {
    val context = LocalContext.current
    var motivoSeleccionado by remember { mutableStateOf("") }
    var diagnostico by remember { mutableStateOf("") }
    val fecha = remember { mutableStateOf(Date().toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA))
            .padding(horizontal = 30.dp), // Añadir margen horizontal
        horizontalAlignment = Alignment.Start // Alinear todo el contenido a la izquierda
    ) {
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 40.dp)
                .size(80.dp)
                .align(Alignment.CenterHorizontally) // Centrar la imagen horizontalmente
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Diagnóstico de $nombre",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF45ACCC),
            modifier = Modifier.fillMaxWidth() // Ocupar todo el ancho disponible
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Doctor: Gregory House",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth() // Ocupar todo el ancho disponible
        )
        Text(
            "Paciente: $nombre",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth() // Ocupar todo el ancho disponible
        )
        CustomDropdownMenuD(
            value = motivoSeleccionado,
            onValueChange = { motivoSeleccionado = it },
            label = "Motivo",
            options = listOf("Ansiedad severa", "Estrés", "Depresión", "Otros"),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Fecha y hora: ${fecha.value}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth() // Ocupar todo el ancho disponible
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Diagnóstico",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth() // Ocupar todo el ancho disponible
        )
        CustomTextField(
            value = diagnostico,
            onValueChange = { diagnostico = it },
            label = "Diagnóstico"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { (context as? ComponentActivity)?.finish() },
            shape = RoundedCornerShape(50),
            modifier = Modifier.align(Alignment.CenterHorizontally) // Centrar el botón horizontalmente
        ) {
            Text("Volver")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp, horizontal = 30.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            singleLine = false, // Permitir múltiples líneas
            maxLines = Int.MAX_VALUE, // No limitar el número de líneas
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp), // Ajustar la altura para permitir más texto
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (isError) Color.Red else Color.Gray,
                unfocusedBorderColor = if (isError) Color.Red else Color.Gray
            )
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenuD(
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically // Centrar verticalmente
        ) {
            Text(
                "Motivo",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f) // Ancho relativo para el texto
            )
            Spacer(modifier = Modifier.width(16.dp)) // Espaciador para separar el texto de la caja
            Box(
                modifier = Modifier
                    .weight(2f) // Ancho relativo para la caja del menú
                    .wrapContentWidth(align = Alignment.End) // Alinear la caja al final
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
                        .width(200.dp)
                        .clickable { expanded = !expanded },
                    enabled = false // Deshabilitar para prevenir enfoque
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(200.dp) // Asegurar el ancho del DropdownMenu
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
}



@Preview(showBackground = true)
@Composable
fun DiagnosticoPreview() {
    ProyectoSISVITATheme {
        MyApp {
            DiagnosticoScreen("Andres Arriel Paladino")
        }
    }
}

