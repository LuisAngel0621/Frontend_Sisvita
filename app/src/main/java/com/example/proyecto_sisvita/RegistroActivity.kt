// RegistroActivity.kt
package com.example.proyecto_sisvita

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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

    val isCorreoValid = correo.endsWith("@unmsm.edu.pe")
    val isEdadValid = edad.toIntOrNull()?.let { it in 18..40 } ?: false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)), // Background color similar to the image
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 30.dp,bottom = 30.dp)) {
            // Logo

            Image(
                painter = painterResource(id = R.drawable.sisvita_logo), // Asegúrate de tener esta imagen en la carpeta drawable
                contentDescription = null,
                modifier = Modifier.size(60.dp).padding(end = 8.dp)
            )
            Text(
                text = "SYSVITA",
                fontSize = 24.sp,
                color = Color(0xFF45ACCC)
            )
        }

        Text(
            text = "Complete sus datos",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomTextField(value = correo, onValueChange = { correo = it }, label = "Correo Institucional", isError = !isCorreoValid && correo.isNotBlank(), errorMessage = "Correo debe terminar en @unmsm.edu.pe")
        CustomTextField(value = nombre, onValueChange = { nombre = it }, label = "Nombre")
        CustomTextField(value = apellidos, onValueChange = { apellidos = it }, label = "Apellidos")
        CustomTextField(value = edad, onValueChange = { edad = it }, label = "Edad", keyboardType = KeyboardType.Number, isError = !isEdadValid && edad.isNotBlank(), errorMessage = "Edad debe estar entre 18 y 40 años")
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
                    if (allFieldsFilled && isCorreoValid && isEdadValid) {
                        context.startActivity(Intent(context, TerminosActivity::class.java))
                    } else {
                        Toast.makeText(context, "Por favor, complete todos los campos correctamente.", Toast.LENGTH_LONG).show()
                    }
                },
                enabled = allFieldsFilled && isCorreoValid && isEdadValid
            ) {
                Text("Continuar")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String, keyboardType: KeyboardType = KeyboardType.Text, isError: Boolean = false, errorMessage: String = "")
{
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 30.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            singleLine = true,  // Ensure single line
            maxLines = 1,       // Ensure max lines is 1
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (isError) Color.Red else Color.Gray,
                unfocusedBorderColor = if (isError) Color.Red else Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
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
        Box(modifier = Modifier
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

@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    ProyectoSISVITATheme {
        RegistroScreen()
    }
}
