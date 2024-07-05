package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Paciente
import com.example.proyecto_sisvita.data.model.UsuarioTipo
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Actualizacion
class PacientesViewModel: ViewModel() {

    /*
    fun addPaciente(paciente: Paciente){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiInstance.apiInstance.datospacienteApiService(paciente)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "201"){
                    println("Paciente Registrado")
                }
            }
        }

    }
    */
    var id_paciente by mutableStateOf(0)
        private  set
    var isloading by mutableStateOf(false)
        private  set
    fun addPaciente(paciente: Paciente, onComplete: (Int)-> Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading = true
            val response = ApiInstance.apiInstance.datospacienteApiService(paciente)
            withContext(Dispatchers.Main){
                if(response.body()!!.codigo == "201"){
                    val cuerpo = response.body()?.data
                    cuerpo?.let{id_paciente = it.id_usu}
                    onComplete(id_paciente)
                    //Revisar
                    //addContrasenia(UsuarioTipo(id_tipo = 1,id_paciente=id_paciente,sesion=true,condiciones=true,terminos = true))
                    println("Paciente Registrado")
                }
                isloading = false
            }
        }

    }

    var id_usuariotipo by mutableStateOf(0)
        private  set
    fun addContrasenia(usuario: UsuarioTipo, onComplete: (Int)-> Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading = true
            val response = ApiInstance.apiInstance.enviarcontrasenia(usuario)
            withContext(Dispatchers.Main){
                if(response.body()!!.status == 201){
                    val cuerpo = response.body()?.data
                    cuerpo?.let{id_usuariotipo = it.id_usutip}
                    onComplete(id_usuariotipo)
                    println(id_usuariotipo)
                    println("Paciente con Contraseña")
                }
                else{
                    println("Paciente sin contraseña")
                }
                isloading = false
            }
        }

    }
}