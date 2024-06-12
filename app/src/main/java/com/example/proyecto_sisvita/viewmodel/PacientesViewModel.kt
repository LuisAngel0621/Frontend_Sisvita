package com.example.proyecto_sisvita.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Paciente
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PacientesViewModel: ViewModel() {

    fun addPaciente(paciente: Paciente){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiInstance.apiInstance.datospacienteApiService(paciente)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "200"){
                    println("Paciente Registrado")
                }
            }
        }

    }
}