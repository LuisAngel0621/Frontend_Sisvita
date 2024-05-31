package com.example.proyecto_sisvita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRealizarTest = findViewById<Button>(R.id.btnRealizarTest)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnRealizarTest.setOnClickListener {
            val intent = Intent(this, CompletarDatosActivity::class.java)
            startActivity(intent)
        }

    }
}