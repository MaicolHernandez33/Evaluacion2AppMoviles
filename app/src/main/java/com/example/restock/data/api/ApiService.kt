package com.example.restock.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClimaService {

    private const val BASE_URL = "https://api.open-meteo.com/v1/"

    val api: ClimaApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ClimaApi::class.java)
}
