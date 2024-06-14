package com.example.proyecto_sisvita.registro.TestIsra.ISRA

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import kotlinx.coroutines.delay

class InicioCognitivo: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                SplashScreen(onCountDownFinished = {})
            }
        }
    }
}

@Composable
fun SplashScreen(onCountDownFinished: () -> Unit) {
    var counter by remember { mutableStateOf(3) }
    var showStartButton by remember { mutableStateOf(false) }
    var categoria by remember { mutableStateOf(0) }
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        delay(1000)
        while (counter > 0) {
            delay(1000)
            counter--
        }
        onCountDownFinished()
        showStartButton = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
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
                modifier = Modifier.size(150.dp).padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "El test comenzará en $counter segundos",
                fontSize = 18.sp,
                color = Color.Black
            )

            if (showStartButton){
                Spacer(modifier = Modifier.height(16.dp))
                ContadorScreen(
                    onStartTestClicked = {
                        categoria = 1
                        guardarCategoria(categoria)
                        context.startActivity(Intent(context, TestISRA::class.java))
                    })

            }
        }
    }
}

fun guardarCategoria(categoria: Int) {
    println("Categoría seleccionada: $categoria")
}

@Composable
fun ContadorScreen(
    onStartTestClicked: () -> Unit) {
    Button(
        onClick = onStartTestClicked,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = "Iniciar Test")
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewInicioCogApp() {
    SplashScreen(onCountDownFinished = {})
}