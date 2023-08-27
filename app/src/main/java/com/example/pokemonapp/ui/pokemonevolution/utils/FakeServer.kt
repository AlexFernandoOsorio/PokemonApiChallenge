package com.example.pokemonapp.ui.pokemonevolution.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakeServer {
    suspend fun <T> makeFakeApiCall(): FakeResponse<String> {
        return withContext(Dispatchers.IO) {
            //delay(5000) // Simulamos una pequeña demora en la llamada

            val randomSuccess = (0..1).random() == 0

            if (randomSuccess) {
                FakeResponse.Success("Respuesta exitosa aleatoria")
            } else {
                FakeResponse.Error("Error en la solicitud aleatoria")
            }
        }
    }
}

sealed class FakeResponse<out T> {
    data class Success<T>(val data: T) : FakeResponse<T>()
    data class Error(val message: String) : FakeResponse<Nothing>()
}