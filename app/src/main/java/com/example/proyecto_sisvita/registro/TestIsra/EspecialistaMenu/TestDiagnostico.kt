//"TestDiagnostico.kt"


package com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
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
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import java.util.Calendar
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
            textAlign = TextAlign.Center,
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp),

        ) {
            Text(
                "Fecha y \nhora: ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )
            showDatePicker(context)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Diagnóstico",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Ocupar todo el ancho disponible
        )
        CustomTextFieldD(
            value = diagnostico,
            onValueChange = { diagnostico = it },
            label = "Diagnóstico"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                context.startActivity(Intent(context, RealizarVigilancia::class.java)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFC3F52) // Rojo claro
                )
            ) {
                Text("Atrás")
            }
            Button(
                onClick = {

                }
            ) {
                Text("Enviar")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFieldD(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 0.dp, horizontal = 0.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            singleLine = false, // Permitir múltiples líneas
            maxLines = Int.MAX_VALUE, // No limitar el número de líneas
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp), // Ajustar la altura para permitir más texto
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


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically // Centrar verticalmente
        ) {
            Text(
                "Motivo: ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f) // Ancho relativo para el texto
            )
            Spacer(modifier = Modifier.width(2.dp)) // Espaciador para separar el texto de la caja
            Box(
                modifier = Modifier
                    .weight(3f) // Ancho relativo para la caja del menú
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
                        .width(250.dp)
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

@Composable
fun showDatePicker(context: Context){

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val time = remember { mutableStateOf("") }
    val showTimePickerDialog = remember { mutableStateOf(false) }

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )

    if (showTimePickerDialog.value) {
        showTimePicker(context) { selectedTime ->
            time.value = selectedTime
            showTimePickerDialog.value = false // Oculta el diálogo después de seleccionar
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { datePickerDialog.show() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9400FF) // Color del botón
                ),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = if (date.value.isEmpty()) "Seleccionar Fecha" else date.value)
            }
            IconButton(onClick = {
                showTimePickerDialog.value = true
            }) {
                Image(
                    painter = painterResource(id = R.drawable.reloj), // Usa tu imagen
                    contentDescription = "Seleccionar Hora",
                    modifier = Modifier.size(36.dp), // Ajusta el tamaño de la imagen
                    contentScale = ContentScale.Fit // Ajusta la escala del contenido
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Fecha: ${if (date.value.isEmpty()) "No seleccionada" else date.value}")
        Text(text = "Hora: ${if (time.value.isEmpty()) "No seleccionada" else time.value}")
    }

}
@Composable
fun showTimePicker(context: Context, onTimeSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            onTimeSelected(formattedTime)
        }, hour, minute, true
    )

    timePickerDialog.show()
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

