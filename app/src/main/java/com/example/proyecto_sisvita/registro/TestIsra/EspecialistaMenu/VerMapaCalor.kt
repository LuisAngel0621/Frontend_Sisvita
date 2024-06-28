package com.example.proyecto_sisvita.registro.TestIsra.EspecialistaMenu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_sisvita.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.heatmaps.HeatmapTileProvider
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VerMapaCalor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentDate = getCurrentDate()
        setContent {
            VerMapaCalorScreen(currentDate)
        }
    }
}

@Composable
fun VerMapaCalorScreen(currentDate: String) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC))
            .padding(vertical = 5.dp, horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sisvita_logo),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("SISVITA", fontSize = 24.sp, color = Color(0xFF45ACCC))
        Spacer(modifier = Modifier.height(8.dp))
        Text("Enviar Diagnóstico", fontSize = 18.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        CustomOutlinedTextField(
            value = currentDate,
            label = "Fecha",
            enabled = false
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "---",
            onValueChange = {},
            label = { Text("Tipo de Test") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "---",
            onValueChange = {},
            label = { Text("Nivel") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Mapa de Calor", color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))

        SimpleMapView(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { context.startActivity(Intent(context, RealizarVigilancia::class.java)) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    label: String,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = Color.DarkGray,
            disabledLabelColor = Color.DarkGray,
            disabledBorderColor = Color.DarkGray,
        )
    )
}

@Composable
fun SimpleMapView(
    modifier: Modifier = Modifier
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(-9.189967, -75.015152), // Centro aproximado de Perú
            5f // Nivel de zoom
        )
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isTrafficEnabled = false),
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
        // Agregar el heatmap en el mapa
        MapEffect { map ->
            val heatmapProvider = HeatmapTileProvider.Builder()
                .data(getHeatmapDataUbigeo())
                .radius(50) // Ajusta el tamaño de los puntos de calor aquí
                .build()
            map.addTileOverlay(TileOverlayOptions().tileProvider(heatmapProvider))
        }
    }

}

fun getHeatmapDataUbigeo(): List<LatLng> {
    val ubigeoCoordinates = mapOf(
        "010101" to LatLng(-6.2294, -77.8714), // Chachapoyas
        "050201" to LatLng(-13.6281, -74.1442), // Cangallo
    )

    // Obtener coordenadas basadas en números de ubigeo
    return listOf(
        ubigeoCoordinates["010101"] ?: LatLng(0.0, 0.0), // Ejemplo de obtención, usa el valor real de ubigeo
        ubigeoCoordinates["050201"] ?: LatLng(0.0, 0.0),
    )
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}

@Preview(showBackground = true)
@Composable
fun VerMapaCalorPreview() {
    VerMapaCalorScreen(currentDate = getCurrentDate())
}
