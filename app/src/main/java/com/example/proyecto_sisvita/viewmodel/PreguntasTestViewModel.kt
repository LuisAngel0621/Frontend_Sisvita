package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Pregunta
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Actualizacion
class PreguntasTestViewModel: ViewModel() {

    var listapreg by mutableStateOf(listOf<Pregunta>())
        private set

    fun getPreguntas(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiInstance.apiInstance.obtenerPreguntas()
            println("Obs1")
            withContext(Dispatchers.Main){
                println("obs")
                if(response.body()!!.status == 201){
                    println("entró")
                    val cuerpo = response.body()?.preguntas
                    cuerpo?.let{listapreg = it.toList()}

                }
            }
        }
    }
}

