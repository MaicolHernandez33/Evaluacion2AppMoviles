package com.example.restock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restock.data.api.ApiClimaService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class ClimaUiState(
    val temp: Double? = null,
    val viento: Double? = null,
    val descripcion: String? = null,
    val sensacion: Double? = null,
    val cargando: Boolean = false,
    val error: String? = null,
    val ultimaHora: String? = null
)

class ClimaViewModel : ViewModel() {

    private val _ui = MutableStateFlow(ClimaUiState())
    val ui: StateFlow<ClimaUiState> = _ui

    fun cargarClima(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _ui.value = ClimaUiState(cargando = true)

                val r = ApiClimaService.api.obtenerClima(lat, lon)

                val temp = r.current_weather.temperature
                val viento = r.current_weather.windspeed
                val desc = descripcionClima(r.current_weather.weathercode)

                // Sensación térmica aproximada
                val sensacionTemp = temp - (viento * 0.1)

                val chileTimeZone = TimeZone.getTimeZone("America/Santiago")

                val formato = SimpleDateFormat("HH:mm", Locale.getDefault())
                formato.timeZone = chileTimeZone

                val hora = formato.format(Date())

                _ui.value = ClimaUiState(
                    temp = temp,
                    viento = viento,
                    descripcion = desc,
                    sensacion = sensacionTemp,
                    cargando = false,
                    ultimaHora = hora
                )

            } catch (e: Exception) {
                _ui.value = ClimaUiState(error = "Error al obtener el clima")
            }
        }
    }

    private fun descripcionClima(code: Int): String {
        return when (code) {
            0 -> "Despejado ☀️"
            1,2 -> "Parcialmente nublado ⛅"
            3 -> "Nublado ☁️"
            45,48 -> "Niebla 🌫️"
            51,53,55 -> "Llovizna 🌦️"
            61,63,65 -> "Lluvia 🌧️"
            71,73,75 -> "Nieve ❄️"
            else -> "Clima desconocido"
        }
    }
}
