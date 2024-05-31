package com.example.proyecto_sisvita

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class CompletarDatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnContinuar = findViewById<Button>(R.id.btn_continuar)
        val btnAtras: Button = findViewById(R.id.btn_atras)

        btnContinuar.setOnClickListener {
            val intent = Intent(this, TerminosActivity::class.java)
            startActivity(intent)
        }

        btnAtras.setOnClickListener {
            finish()
        }
    }
}

