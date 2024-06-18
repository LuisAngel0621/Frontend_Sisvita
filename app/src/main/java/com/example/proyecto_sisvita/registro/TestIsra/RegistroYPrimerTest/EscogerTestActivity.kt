package com.example.proyecto_sisvita.registro.TestIsra.RegistroYPrimerTest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.R
import com.example.proyecto_sisvita.registro.TestIsra.ISRA.InicioCognitivo
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme

class EscogerTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                EscogerTestScreen()
            }
        }
    }
}

@Composable
fun EscogerTestScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Test de TEST",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {context.startActivity(Intent(context, InicioCognitivo::class.java))},
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("ISRA")
        }

        Button(
            onClick = { /* Handle HAS */ },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("HAS")
        }

        Button(
            onClick = { /* Handle STAI */ },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("STAI")
        }

        Button(
            onClick = { (context as? ComponentActivity)?.finish() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFC3F52) // Rojo claro
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Atrás")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EscogerTestScreenPreview() {
    ProyectoSISVITATheme {
        EscogerTestScreen()
    }
}

