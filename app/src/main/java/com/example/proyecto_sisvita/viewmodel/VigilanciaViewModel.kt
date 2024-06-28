package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Diagnostico
import com.example.proyecto_sisvita.data.model.Respuestas
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VigilanciaViewModel: ViewModel() {
    var diagnosticos : ArrayList<Diagnostico> by mutableStateOf(arrayListOf())
    val answers = mutableStateListOf<Respuestas>()

    init {
        realizarVigilancia()
    }
    fun realizarVigilancia(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = ApiInstance.apiInstance.realizarvigilancia()
                if (response.isSuccessful) {
                    diagnosticos = response.body()!!.data as ArrayList<Diagnostico>
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