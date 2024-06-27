package com.example.proyecto_sisvita.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Paciente
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VigilanciaViewModel: ViewModel() {
    fun realizarVigilancia(){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiInstance.apiInstance.realizarvigilancia()
            withContext(Dispatchers.Main){
                if(response.body()!!.success == true){
                    println("Paciente Registrado")
                }
            }
        }

    }
}