package com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.proyecto_sisvita.registro.TestIsra.Login.LoginActivity
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme

class MenuRevisar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                MyApp {
                    MenuRevisarScreen()
                }
            }
        }
    }
}

@Composable
fun MenuRevisarScreen(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "menurevisar") {
        composable("menurevisar") { MenuRevisarContent(navController) }
        composable("pendientes") {
            context.startActivity(Intent(context, RealizarVigilancia::class.java))
        }
        composable("revisados") { RevisadosScreen() }
    }
}

@Composable
fun MenuRevisarContent(navController: NavHostController) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Fondo de pantalla
        Image(
            painter = painterResource(id = R.drawable.fondo), // fondo.jpg
            contentDescription = null, // No es necesario para una imagen de fondo
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Contenido centrado
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)) {
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
            Spacer(modifier = Modifier.height(16.dp))
            Text("Bienvenido ", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            // ACÁ LA IMAGEN REFERENCIAL AL ESPECIALISTA
            Image(
                painter = painterResource(id = R.drawable.especialista), // tu logo
                contentDescription = "Logo",
                modifier = Modifier.size(130.dp),
                contentScale = ContentScale.Fit
            )
            // ACÁ LA IMAGEN REFERENCIAL AL ESPECIALISTA
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate("pendientes") },
                modifier = Modifier.padding(horizontal = 32.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("REALIZAR VIGILANCIA")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("revisados") },
                modifier = Modifier.padding(horizontal = 32.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("SEGUIMIENTO")
            }
            Spacer(modifier = Modifier.height(64.dp))
            Button(
                onClick = { context.startActivity(Intent(context, LoginActivity::class.java))},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFC3F52) // Rojo claro
                ),
                modifier = Modifier.padding(horizontal = 32.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("CERRAR SESIÓN")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProyectoSISVITATheme {
        MyApp {
            MenuRevisarScreen()
        }
    }
}
