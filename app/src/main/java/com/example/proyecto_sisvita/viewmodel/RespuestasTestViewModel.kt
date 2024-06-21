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

class RespuestasTestViewModel: ViewModel(){
    var _listaRespuestas: List<ArrayList<String>> by mutableStateOf(arrayListOf())

    fun getRespuestas(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiInstance.apiInstance.obtenerRespuestas()
            if(response.isSuccessful){
                withContext(Dispatchers.Main){
                    val prueba = response.body()?.respuestas
                    if(response.isSuccessful){
                        if (prueba!= null) {
                            _listaRespuestas = prueba.map {it.toArrayList()}
                        }
                    }
                }
            }else{
                println("Fallo en la recoleccion de data")
            }
        }
    }
}
