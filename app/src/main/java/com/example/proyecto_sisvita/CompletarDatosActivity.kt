package com.example.proyecto_sisvita

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

class CompletarDatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnContinuar = findViewById<Button>(R.id.btn_continuar)
        val btnAtras: Button = findViewById(R.id.btn_atras)

        val emailField = findViewById<EditText>(R.id.email)
        val nombreField = findViewById<EditText>(R.id.nombre)
        val apellidosField = findViewById<EditText>(R.id.apellidos)
        val edadField = findViewById<EditText>(R.id.edad)
        val sexoSpinner = findViewById<Spinner>(R.id.sexo)
        val estadoCivilSpinner = findViewById<Spinner>(R.id.estado_civil)
        val ocupacionField = findViewById<EditText>(R.id.ocupacion)

        btnContinuar.setOnClickListener {
            val email = emailField.text.toString().trim()
            val nombre = nombreField.text.toString().trim()
            val apellidos = apellidosField.text.toString().trim()
            val edad = edadField.text.toString().trim()
            val sexo = sexoSpinner.selectedItem.toString().trim()
            val estadoCivil = estadoCivilSpinner.selectedItem.toString().trim()
            val ocupacion = ocupacionField.text.toString().trim()

            if (email.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || edad.isEmpty() || sexo.isEmpty() || estadoCivil.isEmpty() || ocupacion.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show()
            } else if (!email.endsWith("@unmsm.edu.pe")) {
                Toast.makeText(this, "Por favor ingrese un correo institucional válido.", Toast.LENGTH_SHORT).show()
            } else if (!edad.isDigitsOnly() || edad.toInt() < 18 || edad.toInt() > 40) {
                Toast.makeText(this, "La edad debe estar entre 18 y 40 años.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this, TerminosActivity::class.java)
                startActivity(intent)
            }
        }

        btnAtras.setOnClickListener {
            finish()
        }
    }
}
