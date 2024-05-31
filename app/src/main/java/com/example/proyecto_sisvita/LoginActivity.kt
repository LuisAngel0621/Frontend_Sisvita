package com.example.proyecto_sisvita

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//import com.github.kittinunf.fuel.jackson.responseObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCorreo: EditText = findViewById(R.id.etCorreo)
        val etContrasena: EditText = findViewById(R.id.etContrasena)
        val btnIniciarSesion: Button = findViewById(R.id.btnIniciarSesion)
        val btnAtras: Button = findViewById(R.id.btnAtras)

        btnIniciarSesion.setOnClickListener {
            val correo = etCorreo.text.toString()
            val contrasena = etContrasena.text.toString()
            if (correo.isNotEmpty() && contrasena.isNotEmpty()) {
                //iniciarSesion(correo, contrasena)
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnAtras.setOnClickListener {
            finish()
        }
    }

    /*private fun iniciarSesion(correo: String, contrasena: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val (_, _, result) = Fuel.post("http://tu-servidor-api.com/login")
                    .body("{\"correo\": \"$correo\", \"contrasena\": \"$contrasena\"}")
                    .responseObject<UsuarioResponse>()

                withContext(Dispatchers.Main) {
                    result.fold(
                        success = { usuario ->
                            if (usuario.codigo > 0) {
                                Toast.makeText(this@LoginActivity, "Bienvenido ${usuario.nombres}", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@LoginActivity, usuario.msj, Toast.LENGTH_SHORT).show()
                            }
                        },
                        failure = {
                            Toast.makeText(this@LoginActivity, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }*/
}

data class UsuarioResponse(
    val codigo: Int,
    val nombres: String,
    val apellidos: String,
    val edad: Int,
    val sexo: String,
    val estadoc: String,
    val ocupacion: String,
    val msj: String
)