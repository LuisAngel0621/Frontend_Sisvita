package com.example.proyecto_sisvita

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.proyecto_sisvita.R

class TerminosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminos)

        val btnNoAcepto = findViewById<Button>(R.id.btn_no_acepto)
        val btnConfirmar = findViewById<Button>(R.id.btn_confirmar)
        val checkboxTerminos = findViewById<CheckBox>(R.id.checkbox_terminos)
        val checkboxNotificaciones = findViewById<CheckBox>(R.id.checkbox_notificaciones)

        btnNoAcepto.setOnClickListener {
            finish()
        }

        btnConfirmar.setOnClickListener {
            if (checkboxTerminos.isChecked) {
                // Proceder con las acciones deseadas
                Toast.makeText(this, "Terminos aceptados", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, iniciartestActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Debe aceptar los términos para continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

