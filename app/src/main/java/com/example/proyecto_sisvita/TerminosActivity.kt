package com.example.proyecto_sisvita

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme

class TerminosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                TerminosScreen()
            }
        }
    }
}

@Composable
fun TerminosScreen() {
    val context = LocalContext.current
    val (aceptarTerminos, setAceptarTerminos) = remember { mutableStateOf(false) }
    val (aceptarNotificaciones, setAceptarNotificaciones) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)), // Background color similar to the image
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = null,
            modifier = Modifier.size(100.dp).padding(bottom = 16.dp)
        )

        Text(
            text = "Terminos y condiciones del test",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .background(Color(0xFFF5EDAD), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = "Al realizar este test psicológico, reconoces y aceptas que los resultados proporcionados son únicamente para fines de entretenimiento y autoconocimiento, y no deben ser considerados como un diagnóstico profesional. Todos los datos recopilados son estrictamente confidenciales y no serán compartidos con terceros sin tu consentimiento expreso, a menos que así lo exija la ley. Es importante destacar que este test no sustituye la opinión de un profesional de la salud mental debidamente calificado. Se requiere que tengas al menos 18 años de edad para participar en esta evaluación. Al iniciar el test, otorgas tu consentimiento informado para participar voluntariamente y comprendes que los resultados obtenidos son responsabilidad tuya y que los creadores de este test no serán responsables de cualquier daño, pérdida o lesión que pueda surgir del uso o interpretación de los resultados del test.",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = aceptarTerminos, onCheckedChange = { setAceptarTerminos(it) })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Aceptar terminos de privacidad y condiciones")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = aceptarNotificaciones, onCheckedChange = { setAceptarNotificaciones(it) })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Aceptas que te mandemos notificaciones para realizar brindar el apoyo")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { (context as? ComponentActivity)?.finish() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFC3F52) // Rojo claro
                )
            ) {
                Text("No acepto")
            }

            Button(
                onClick = {
                    if (aceptarTerminos && aceptarNotificaciones) {
                        context.startActivity(Intent(context, IniciarTestActivity::class.java))
                    }
                },
                enabled = aceptarTerminos && aceptarNotificaciones
            ) {
                Text("Confirmar")
            }
        }
    }
}
