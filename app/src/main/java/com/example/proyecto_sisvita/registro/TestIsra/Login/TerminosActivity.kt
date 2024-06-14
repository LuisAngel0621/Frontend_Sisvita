package com.example.proyecto_sisvita.registro.TestIsra.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.R
import com.example.proyecto_sisvita.data.model.Paciente
import com.example.proyecto_sisvita.registro.TestIsra.escogerTest.IniciarTestActivity
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import com.example.proyecto_sisvita.viewmodel.PacientesViewModel

class TerminosActivity() : ComponentActivity() {

    val viewModel by viewModels<PacientesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellidos = intent.getStringExtra("apellidos") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""
        val edad = intent.getStringExtra("edad") ?: ""
        val sexo = intent.getStringExtra("sexo") ?: ""
        val estadocivil = intent.getStringExtra("estadocivil") ?: ""
        val ocupacion = intent.getStringExtra("ocupacion") ?: ""

        setContent {
            ProyectoSISVITATheme {
                TerminosScreen(nombres = nombre,
                    apellidos = apellidos,
                    correo = correo,
                    edad = edad,
                    sexo = sexo,
                    estadocivil = estadocivil,
                    ocupacion = ocupacion,
                    viewModel = viewModel)
            }
        }
    }
}

@Composable
fun TerminosScreen(nombres: String,apellidos: String,correo: String,edad: String,sexo: String,estadocivil: String,ocupacion: String, viewModel: PacientesViewModel) {
    val context = LocalContext.current
    val (aceptarTerminos, setAceptarTerminos) = remember { mutableStateOf(false) }
    val (aceptarNotificaciones, setAceptarNotificaciones) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)).padding(bottom = 100.dp) // Background color similar to the image
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = null,
            modifier = Modifier.size(100.dp).align(alignment = Alignment.CenterHorizontally)

        )

        Text(
            text = "Terminos y condiciones del test",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 20.dp).align(alignment = Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier.padding(vertical = 25.dp, horizontal = 20.dp)
                .background(Color(0xFFF5EDAD), shape = RoundedCornerShape(8.dp))
                .padding(30.dp)
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

        Spacer(modifier = Modifier.height(30.dp))

        Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)) {
            Checkbox(checked = aceptarTerminos, onCheckedChange = { setAceptarTerminos(it) })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Aceptar terminos de privacidad y condiciones", fontSize = 15.sp)
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)) {
            Checkbox(checked = aceptarNotificaciones, onCheckedChange = { setAceptarNotificaciones(it) })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Aceptas que te mandemos notificaciones para realizar brindar el apoyo", fontSize = 15.sp)

        }

        Spacer(modifier = Modifier.height(40.dp))

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
                    if (aceptarTerminos) {

                        viewModel.addPaciente(

                            Paciente(
                                nombres = nombres,
                                apellidos = apellidos,
                                correoinstitucional = correo,
                                edad = edad.toInt(),
                                sexo = sexo,
                                estadocivil = estadocivil,
                                ocupacion = ocupacion
                            )
                        )
                        context.startActivity(Intent(context, IniciarTestActivity::class.java))
                    }
                },
                enabled = aceptarTerminos
            ) {
                Text("Confirmar")
            }
        }
    }
}

@Preview()
@Composable
fun TerminosActivityScreenPreview(
    nombre: String,
    apellidos: String,
    correo: String,
    edad: String,
    sexo: String,
    estadocivil: String,
    ocupacion: String,
    viewModel: PacientesViewModel
) {
    ProyectoSISVITATheme {
        TerminosScreen(
            nombres = nombre,
            apellidos = apellidos,
            correo = correo,
            edad = edad,
            sexo = sexo,
            estadocivil = estadocivil,
            ocupacion = ocupacion,
            viewModel = viewModel
        )
    }
}
