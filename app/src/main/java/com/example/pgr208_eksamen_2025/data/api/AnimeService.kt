package com.example.pgr208_eksamen_2025.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// ===Anime Service===

// Definerer API-endepunktene Retrofit kan kalle.

interface AnimeService {

    // Henter en liste med anime (fra /anime).
@GET("anime")
suspend fun getAnimeList(): Response<AnimeResponse>


// Henter en anime via id (fra (anime/{id}).
@GET("anime/{id}")
suspend fun getAnimeById(
    @Path("id") id: Int
): Response<AnimeIdResponse>

}