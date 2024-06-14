package com.example.proyecto_sisvita.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.UsuarioTest
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestViewModel: ViewModel() {

    fun addTest(test: UsuarioTest){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiInstance.apiInstance.respuestaspacienteApiService(test)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "200"){
                    println("Test Registrado")
                }
            }
        }

    }
}