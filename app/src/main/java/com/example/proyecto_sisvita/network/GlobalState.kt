package com.example.proyecto_sisvita.network

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object GlobalState {
    var id_tipo by mutableStateOf(0)
    var id_user by mutableStateOf(0)
    var id_user_test by mutableStateOf(0)
    var id_usutip by mutableStateOf(0)
    var username by  mutableStateOf("")
    var password by  mutableStateOf("")
    var id_diag by mutableStateOf(0)
    var puntaje by mutableStateOf(0)
    var nivel_ansiedad by mutableStateOf("")
}