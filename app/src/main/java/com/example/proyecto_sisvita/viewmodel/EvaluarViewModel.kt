package com.example.proyecto_sisvita.viewmodel

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

    fun editEvaluacionTest(id_diag: Int,evaluarTestRequest: EvaluarTestRequest){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiInstance.apiInstance.evaluatest(id_diag, evaluarTestRequest)
            withContext(Dispatchers.Main){
                if(response.body()!!.status == 201){
                    println("Evaluacion exitosa")
                }
            }
        }

    }
    fun postNotificarEval(notificarRequest: NotificarRequest){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiInstance.apiInstance.notificarresult(notificarRequest)
            withContext(Dispatchers.Main){
                if(response.body()!!.status == 201){
                    println("Notificacion exitosa")
                }
            }
        }

    }
}