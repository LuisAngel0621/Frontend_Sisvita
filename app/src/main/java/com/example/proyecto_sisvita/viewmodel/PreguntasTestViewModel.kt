package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PreguntasTestViewModel: ViewModel() {
    var _listaPreguntas: List<ArrayList<String>> by mutableStateOf(arrayListOf())

    fun getPreguntas(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiInstance.apiInstance.obtenerPreguntas()
            if(response.isSuccessful){
                withContext(Dispatchers.Main){
                    val prueba = response.body()?.preguntas
                    if(response.isSuccessful){
                        if (prueba!= null) {
                            _listaPreguntas = prueba.map {it.toArrayList()}
                        }
                    }
                }
            }else{
                println("Fallo en la recoleccion de data")
            }
        }
    }
}

