package com.example.proyecto_sisvita.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.proyecto_sisvita.data.model.Respuestas

class QuestionViewModel : ViewModel() {
    val answers = mutableStateListOf<Respuestas>()
}