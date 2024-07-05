package com.example.proyecto_sisvita.registro.TestIsra.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.R
import com.example.proyecto_sisvita.data.model.LoginRequest
import com.example.proyecto_sisvita.network.GlobalState
import com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu.MenuRevisar
import com.example.proyecto_sisvita.registro.TestIsra.TestApi.VistaDatos
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme
import com.example.proyecto_sisvita.viewmodel.LoginViewModel

class LoginActivity : ComponentActivity() {
    val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSISVITATheme {
                LoginScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val context = LocalContext.current
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val usernameRec by viewModel.username.collectAsState()
    val passwordRec by viewModel.password.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)).padding(vertical = 5.dp, horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = null,
            modifier = Modifier.size(100.dp).padding(bottom = 16.dp)
        )

        Text(
            text = "Iniciar Sesión",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = usernameRec,
            onValueChange = { viewModel.onUsernameChange(it)},
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = passwordRec,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.inicioSesion(
                    LoginRequest(
                        correoinstitucional = usernameRec,
                        contraseña = passwordRec)
                ){
                    validacion ->
                    if(validacion == "success"){
                        GlobalState.id_usutip = viewModel.idUsuario?.value!!
                        GlobalState.username = usernameRec
                        GlobalState.password = passwordRec
                        context.startActivity(Intent(context, MenuRevisar::class.java))
                    }
                }
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { context.startActivity(Intent(context, MainActivity::class.java)) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFC3F52) // Rojo claro
            ),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text("Atras")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { context.startActivity(Intent(context, VistaDatos::class.java)) },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text("TEST DATA")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ProyectoSISVITATheme {
        LoginScreen(viewModel = LoginViewModel())
    }
}
