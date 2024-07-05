// MainActivity.kt
package com.example.proyecto_sisvita.registro.TestIsra.Login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.example.proyecto_sisvita.registro.TestIsra.RegistroYPrimerTest.RegistroScreen
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import com.example.proyecto_sisvita.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                MyApp {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}
@Composable
fun MainScreen(navController: NavHostController = rememberNavController(), viewModel: LoginViewModel) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainContent(navController) }
        composable("registro") { RegistroScreen() }
        composable("login") { LoginScreen(viewModel) }
    }
}
@Composable
fun MainContent(navController: NavHostController) {
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
            Image(
                painter = painterResource(id = R.drawable.sisvita_logo2), // tu logo
                contentDescription = "Logo",
                modifier = Modifier.size(130.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.fisi), // tu logo
                contentDescription = "Logo",
                modifier = Modifier.size(130.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("SISVITA", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate("registro") },
                modifier = Modifier.padding(horizontal = 32.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("Realizar Test")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.padding(horizontal = 32.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("Log In")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProyectoSISVITATheme {
        MyApp {
            MainScreen(viewModel = LoginViewModel())
        }
    }
}