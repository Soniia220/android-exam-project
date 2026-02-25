package com.example.pgr208_eksamen_2025.screens.animeRandom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel
import com.example.pgr208_eksamen_2025.data.repository.AnimeRepository
import kotlinx.coroutines.launch



// ===RANDOM VIEWMODEL===

// Henting av tilfeldig anime fra API-kall.

class AnimeRandomViewModel : ViewModel() {

    private val repository = AnimeRepository()

    // ===STATE===
    //Resultatet av tilfeldig valgt anime.
    val randomAnime = mutableStateOf<AnimeApiModel?>(null)


    //===DATA LOADING===
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)


    // ===HENTER TILFELDIG ANIME===
    fun generateAnime(){

        // Hindrer nye kall dersom et kall allerede pågår.
        if (isLoading.value) return

        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null

            //Henter anime listen og velger tilfeldig anime.
            try {
                val liste = repository.getAnimeList()

                if (liste.isNotEmpty()){
                    val valgt = liste.random()
                    randomAnime.value = valgt
                } else {
                    randomAnime.value = null
                    errorMessage.value = "Fant ingen anime, prøv igjen"
                }
            } catch (e: Exception) {
                randomAnime.value = null
                errorMessage.value = e.message ?: "Ukjent feil"
            }
            isLoading.value = false


        }
    }

}