package com.example.proyecto_sisvita.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.LoginRequest
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: ViewModel() {
    lateinit var validacion: String
    fun inicioSesion(loginRequest: LoginRequest){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiInstance.apiInstance.iniciarSesion(loginRequest)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "201"){
                    validacion = "succes"
                    println("Inicio de Sesion Realizado")
                }
            }
        }

    }
}