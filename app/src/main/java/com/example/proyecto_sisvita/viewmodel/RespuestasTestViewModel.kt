package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Escala
import com.example.proyecto_sisvita.data.model.Respuesta
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Actualizacion
class RespuestasTestViewModel: ViewModel(){

    var listaresp by mutableStateOf(listOf<Respuesta>())

    fun getRespuestas(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiInstance.apiInstance.obtenerRespuestas()
            withContext(Dispatchers.Main){
                if (response.body()!!.status == 201){
                    val cuerpo = response.body()?.respuestas
                    cuerpo?.let{listaresp = it.toList()}
                }
            }
        }
    }

    var listaEscala by mutableStateOf(listOf<Escala>())

    fun getEscala(){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiInstance.apiInstance.obtenerEscala()
            withContext(Dispatchers.Main){
                if(response.body()!!.status == 201){
                    val cuerpo = response.body()?.escalas
                    cuerpo?.let { listaEscala = it.toList() }
                }
            }
        }
    }
}
