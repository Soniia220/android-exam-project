package com.example.pgr208_eksamen_2025.data.repository

import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel
import com.example.pgr208_eksamen_2025.data.api.AnimeIdResponse
import com.example.pgr208_eksamen_2025.data.api.AnimeResponse
import com.example.pgr208_eksamen_2025.data.api.JikanApi

// ===ANIME REPOSITORY===

// Håndterer datatilgang for ViewModels og kapsler inn API-kall.


class AnimeRepository {
    private val api = JikanApi.animeService


// Henter liste med anime fra API-et
// Returnerer en tom liste vis respons feiler.
    suspend fun getAnimeList(): List<AnimeApiModel> {
        val response = api.getAnimeList()

        if (response.isSuccessful) {
            val body: AnimeResponse? = response.body()
            return body?.data ?: emptyList()
        } else {

            return emptyList()
        }
    }

    // Henter en anime basert på id
    // Returnerer null vis API-kallet feiler
    suspend fun getAnimeById(id: Int): AnimeApiModel? {
        val response = api.getAnimeById(id)

        return if (response.isSuccessful) {
            val body: AnimeIdResponse? = response.body()
            body?.data
        }else {
            null

        }
    }
}
