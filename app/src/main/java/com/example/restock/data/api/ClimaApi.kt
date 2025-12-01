package com.example.restock.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ClimaApi {

    @GET("forecast")
    suspend fun obtenerClima(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true
    ): ClimaResponse
}
