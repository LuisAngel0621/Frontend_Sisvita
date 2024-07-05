package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.Diagnostico
import com.example.proyecto_sisvita.data.model.Nivel
import com.example.proyecto_sisvita.data.model.UsuarioTest
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestViewModel: ViewModel() {
    var id_usertest by mutableStateOf(0)
        private  set
    var isloading by mutableStateOf(false)
        private  set
    fun addTest(test: UsuarioTest, onComplete:(Int)-> Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading = true
            val response = ApiInstance.apiInstance.respuestaspacienteApiService(test)
            withContext(Dispatchers.Main){
                if(response.body()!!.message == "Registro de Test exitoso"){
                    val cuerpo = response.body()!!.id_user_test
                    id_usertest = cuerpo
                    onComplete(id_usertest)
                    println(id_usertest)
                    println("Test Registrado")
                }
                else{
                    println("Test No registrado")
                }
                isloading = false
            }
        }

    }
    var suma_puntaje by mutableStateOf(0)
        private  set
    fun sumEscala(id_paciente: Int, onComplete: (Int) -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading = true
            println(id_paciente)
            val response = ApiInstance.apiInstance.sumapuntaje(id_paciente)
            println(response.body())
            withContext(Dispatchers.Main){
                if(response.body()!!.success == true){
                    val cuerpo = response.body()!!.suma
                    suma_puntaje = cuerpo
                    onComplete(suma_puntaje)
                    println("Puntaje Obtenido")
                }
                else{
                    println("Error en Puntaje")
                }
                isloading = false
            }
        }
    }


    var id_diag by mutableStateOf(0)
        private  set
    fun resultadoTest(diagnostico: Diagnostico, onComplete: (Int) -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading = true
            val response = ApiInstance.apiInstance.diagnosticoprevio(diagnostico)
            withContext(Dispatchers.Main){
                if (response.body()!!.success == true){
                    val cuerpo = response.body()!!.id_diag
                    id_diag = cuerpo
                    onComplete(id_diag)
                    println("Diagnostico previo realizado")
                }
                else{
                    println("Diagnositco previo no realizado")
                }
                isloading = false
            }

        }
    }

    var listaNivel by mutableStateOf(listOf<Nivel>())
    fun getNivel(onComplete: (List<Nivel>) -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading = true
            val response = ApiInstance.apiInstance.obtenerNivel()
            withContext(Dispatchers.Main){
                if(response.body()!!.status == 201){
                    val cuerpo = response.body()?.niveles
                    cuerpo?.let { listaNivel = it.toList() }
                    onComplete(listaNivel)
                }
                else{
                    println("No hay niveles")
                }
                isloading = false
            }
        }
    }
}