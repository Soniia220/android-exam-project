package com.example.pgr208_eksamen_2025.screens.animeHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgr208_eksamen_2025.data.repository.AnimeRepository
import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State



// ===HOME VIEWMODEL===

// Laster anime-listen ved oppstart og eksponerer state til UI.
class AnimeHomeViewModel : ViewModel() {


// ===REPOSITORY===
    private val repository = AnimeRepository()

    // ===STATE===
    private val _animeList = mutableStateOf<List<AnimeApiModel>>(emptyList())
    val animeList: State<List<AnimeApiModel>> = _animeList

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage


    // ===HOME VIEWMODEL===
    // Laster data når ViewModel opprettes
    init {
        loadAnime()
    }

    // ===LOAD DATA===

    // Henter anime-liste fra repository og oppdaterer UI-state
    private fun loadAnime() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val list = repository.getAnimeList()
                _animeList.value = list
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Ukjent feil"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
