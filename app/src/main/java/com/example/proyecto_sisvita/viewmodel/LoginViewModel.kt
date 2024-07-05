package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.LoginRequest
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _idUsuario = MutableStateFlow<Int?>(0)
    val idUsuario: StateFlow<Int?> = _idUsuario

    var validacion_login by mutableStateOf("")
        private  set
    var isloading by mutableStateOf(false)
        private  set
   //lateinit var validacion: String
    fun inicioSesion(loginRequest: LoginRequest, onComplete:(String)->Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading = true
            val response = ApiInstance.apiInstance.iniciarSesion(loginRequest)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "201"){
                    validacion_login = "success"
                    _idUsuario.value = response.body()?.id_especialista
                    onComplete(validacion_login)
                    println("Inicio de Sesion Realizado")
                }else{
                    println("Error en el Inicio de Sesion")
                }
                isloading = false
            }
        }
    }
    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }
}
