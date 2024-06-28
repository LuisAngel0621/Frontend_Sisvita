package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Diagnostico
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VigilanciaViewModel: ViewModel() {
    var diagnosticos = mutableStateListOf<Diagnostico>()

    init {
        realizarVigilancia()
    }

    fun realizarVigilancia() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiInstance.apiInstance.realizarvigilancia()
                if (response.isSuccessful && response.body()?.success == true) {
                    val receivedDiagnosticos = response.body()?.data ?: emptyList()
                    diagnosticos.clear()
                    diagnosticos.addAll(receivedDiagnosticos)
                } else {
                    // Manejar error de respuesta
                    println("Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                // Manejar excepción
                println("Excepción durante la llamada a la API: ${e.message}")
            }
        }
    }
}