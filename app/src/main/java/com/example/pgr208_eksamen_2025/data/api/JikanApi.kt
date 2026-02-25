package com.example.pgr208_eksamen_2025.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// ===Jikan API client===

// Opretter Retrofit-instansen appen bruker for å komunisere med anime API-et.

object JikanApi {


// HTTP-klient med logging av nettverkskall
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

// Retrofit-konfigurasjon med base-URL og JSON-konvertering
    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://api.jikan.moe/v4/")
        .addConverterFactory(GsonConverterFactory.create()
        ).build()


    // Service som gir tilgang til API-endepunktene
val animeService: AnimeService = _retrofit.create(AnimeService::class.java)
}