package com.example.pgr208_eksamen_2025.screens.animeSearch


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel
import com.example.pgr208_eksamen_2025.data.repository.AnimeRepository
import kotlinx.coroutines.launch


// === SEARCH VIEWMODEL===

class AnimeSearchViewModel : ViewModel() {

    private val repository = AnimeRepository()

    // Input: Id som tekst (brukeren skriver string)
    val searchId = mutableStateOf("")

    //Output: funnet anime eller null
    val animeResult = mutableStateOf<AnimeApiModel?>(null)

    // Status
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)


    // ===SØKE LOGIKK===
    fun search() {
        val id = searchId.value.toIntOrNull()

        // Validerer input før API-kall
        if (id == null) {
            errorMessage.value = "Id må være et tall"
            animeResult.value = null
            return
        }

        viewModelScope.launch {
            try {
                isLoading.value = true
                errorMessage.value = null

                val result = repository.getAnimeById(id)

                // Finner anime eller gir feilmelding)
                if (result != null) {
                    animeResult.value = result
                }else {
                    animeResult.value = null
                    errorMessage.value = "Anime med id $id finnes ikke"
                }
                } catch (e: Exception) {
                    animeResult.value = null
                errorMessage.value = e.message ?: "Ukjent feil"
            } finally {
                isLoading.value = false
            }
            }
        }
    }