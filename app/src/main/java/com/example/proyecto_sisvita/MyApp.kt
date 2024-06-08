// MyApp.kt
package com.example.proyecto_sisvita

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.proyecto_sisvita.ui.theme.ProyectoSISVITATheme

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ProyectoSISVITATheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}
