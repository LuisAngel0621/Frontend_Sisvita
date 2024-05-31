package com.example.proyecto_sisvita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class iniciartestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciartest)

        val btnIniciarTest = findViewById<Button>(R.id.startTestButton)

        btnIniciarTest.setOnClickListener {
            Toast.makeText(this, "Escoger el test...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, escogertestActivity::class.java)
            startActivity(intent)
        }
    }


}