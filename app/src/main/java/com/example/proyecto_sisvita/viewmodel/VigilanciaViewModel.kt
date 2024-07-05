package com.example.proyecto_sisvita.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Diagnostico
import com.example.proyecto_sisvita.data.model.Nivel
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    val diagnostico = mutableStateOf<Diagnostico?>(null)
    fun realizarVigilanciaEspecifica(id_diag: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiInstance.apiInstance.realizarVigilanciaEspecifica(id_diag)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        val diagnosticoResponse: Diagnostico? = response.body()?.data
                        diagnostico.value = diagnosticoResponse
                    } else {
                        // Manejar error de respuesta
                        println("Error en la respuesta: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                // Manejar excepción
                println("Excepción durante la llamada a la API: ${e.message}")
            }
        }
    }
}