// RegistroActivity.kt
package com.example.proyecto_sisvita

import android.content.Intent
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme

class RegistroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                RegistroScreen()
            }
        }
    }
}

@Composable
fun RegistroScreen() {
    var correo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var estadoCivil by remember { mutableStateOf("") }
    var ocupacion by remember { mutableStateOf("") }

    val context = LocalContext.current

    val allFieldsFilled = correo.isNotBlank() && nombre.isNotBlank() && apellidos.isNotBlank() &&
            edad.isNotBlank() && sexo.isNotBlank() && estadoCivil.isNotBlank() && ocupacion.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)), // Background color similar to the image
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo), // Asegúrate de tener esta imagen en la carpeta drawable
            contentDescription = null,
            modifier = Modifier.size(100.dp).padding(bottom = 16.dp)
        )

        Text(
            text = "Complete sus datos",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomTextField(value = correo, onValueChange = { correo = it }, label = "Correo Institucional")
        CustomTextField(value = nombre, onValueChange = { nombre = it }, label = "Nombre")
        CustomTextField(value = apellidos, onValueChange = { apellidos = it }, label = "Apellidos")
        CustomTextField(value = edad, onValueChange = { edad = it }, label = "Edad", keyboardType = KeyboardType.Number)
        CustomDropdownMenu(value = sexo, onValueChange = { sexo = it }, label = "Sexo", options = listOf("Masculino", "Femenino"))
        CustomDropdownMenu(value = estadoCivil, onValueChange = { estadoCivil = it }, label = "Estado Civil", options = listOf("Soltero", "Casado", "Divorciado", "Viudo"))
        CustomTextField(value = ocupacion, onValueChange = { ocupacion = it }, label = "Ocupación")

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                context.startActivity(Intent(context, MainActivity::class.java)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFC3F52) // Rojo claro
                )
            ) {

                Text("Atrás")
            }
            Button(
                onClick = {
                    if (allFieldsFilled) {
                        context.startActivity(Intent(context, TerminosActivity::class.java))
                    }
                },
                enabled = allFieldsFilled
            ) {
                Text("Continuar")
            }
        }
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String, keyboardType: KeyboardType = KeyboardType.Text) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}

@Composable
fun CustomDropdownMenu(value: String, onValueChange: (String) -> Unit, label: String, options: List<String>) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clickable { expanded = !expanded } // Clickable on the entire Box
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(value) },
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { expanded = !expanded }
                )
            },
            modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded }
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

