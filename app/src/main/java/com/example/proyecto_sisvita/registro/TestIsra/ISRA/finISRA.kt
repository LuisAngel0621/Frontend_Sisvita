
package com.example.proyecto_sisvita.registro.TestIsra.ISRA

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.R
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme

class finISRA: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                finScreen()
            }
        }
    }
}

@Composable
fun finScreen() {
    var counter by remember { mutableStateOf(3) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Test Fase Cognitiva",
                fontSize = 40.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.sisvita_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Se registraron tus respuestas de la fase cognitiva",
                fontSize = 30.sp,
                color = Color.Black,
            )
            Button(
                onClick = { /* Acción al enviar el cuestionario */ },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Continuar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFin() {
    finScreen()
}