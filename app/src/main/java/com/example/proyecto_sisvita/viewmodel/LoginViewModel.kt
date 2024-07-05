package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.LoginRequest
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: ViewModel() {
    //lateinit var validacion: String
    var validacion_login by mutableStateOf("")
        private  set
    var isloading by mutableStateOf(false)
        private  set
    lateinit var validacion: String
    fun inicioSesion(loginRequest: LoginRequest, onComplete:(String)->Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading = true
            val response = ApiInstance.apiInstance.iniciarSesion(loginRequest)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "201"){
                    validacion_login = "success"
                    onComplete(validacion_login)
                    println("Inicio de Sesion Realizado")
                }else{
                    println("Error en el Inicio de Sesion")
                }
                isloading = false
            }
        }

    }
}
/*class LoginViewModel: ViewModel() {
    var validacion by mutableStateOf(String())

    fun inicioSesion(loginRequest: LoginRequest){
        viewModelScope.launch(Dispatchers.IO){
            println(loginRequest)
            val response = ApiInstance.apiInstance.iniciarSesion(loginRequest)
            println(response.body())
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null && responseBody.codigo == "201"){
                        validacion = "success"
                        println("Inicio de Sesion Realizado")
                    } else {
                        validacion = "failed"
                    }
                } else {
                    validacion = "failed"
                }
            }
        }
    }

} */