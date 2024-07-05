package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_sisvita.data.model.EvaluarTestRequest
import com.example.proyecto_sisvita.data.model.LoginRequest
import com.example.proyecto_sisvita.data.model.NotificarRequest
import com.example.proyecto_sisvita.network.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EvaluarViewModel: ViewModel() {
    var validacion_eval by mutableStateOf("")
        private  set
    var isloading_eval by mutableStateOf(false)
        private  set

    var validacion_noti by mutableStateOf("")
        private  set
    var isloading_noti by mutableStateOf(false)
        private  set


    fun editEvaluacionTest(id_diag: Int,evaluarTestRequest: EvaluarTestRequest,onComplete:(String)-> Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading_eval = true
            val response = ApiInstance.apiInstance.evaluatest(id_diag, evaluarTestRequest)
            withContext(Dispatchers.Main){
                if(response.body()!!.status == 201){
                    validacion_eval = "Evaluacion exitosa"
                    onComplete(validacion_eval)
                    println("Evaluacion exitosa")
                }else{
                    println("Evaluacion fallida")
                }
                isloading_eval = false
            }
        }
    }
    fun postNotificarEval(notificarRequest: NotificarRequest,onComplete:(String)-> Unit){
        viewModelScope.launch(Dispatchers.IO){
            isloading_noti = true
            val response = ApiInstance.apiInstance.notificarresult(notificarRequest)
            withContext(Dispatchers.Main){
                if(response.body()!!.status == 201){
                    validacion_noti = "Notificacion exitosa"
                    onComplete(validacion_noti)
                    println("Notificacion exitosa")
                }else{
                    println("No se envio la notificacion")
                }
            }
        }
    }
}