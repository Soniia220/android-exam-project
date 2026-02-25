package com.example.pgr208_eksamen_2025.screens.animeIdeas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgr208_eksamen_2025.data.Room.AnimeDbRepository
import com.example.pgr208_eksamen_2025.data.Room.AnimeIdea
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Arver fra ViewModel
class AnimeIdeasViewModel : ViewModel(){

    //Variabel som holder listen av Anime Idea. Denne oppdateres når vi laster oppdaterer, legger til eller sletter anime ideer.
    private val _animeIdeas = MutableStateFlow<List<AnimeIdea>>(emptyList())

    // Gjør at UI kan se listen uten å endre på den.
    val animeIdeas: StateFlow<List<AnimeIdea>> = _animeIdeas.asStateFlow()

    // Variabel som holder på boolean verdi, som holder styr på om dataen loader.
    //Settes til true når vi henter data fra DB, og false når den er ferdig.
    private val _isLoading = MutableStateFlow(false)

    // Kun lesbar StateFlow for å vise en loading indikasjon
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    // Laster inn Anime Ideas med en gang viewmodel opprettes.
    init {
        loadAnimeIdeas()
    }
    //===Funsksjoner===
    //  Henter Anime ideer fra Databasen.
    fun loadAnimeIdeas() {
        viewModelScope.launch {
            //Setter isLoading til true mens data hentes
            _isLoading.value = true
            try {
                //Henter listen med animeideas fra databasen.
                val ideas = AnimeDbRepository.getAllAnimeIdeas()
                // Oppdaterer listen med animeideas i Viewmodel
                _animeIdeas.value = ideas
            } catch (e: Exception) {
                // Hvis noe går galt setter vi bare listen til tom
                _animeIdeas.value = emptyList()
                // Inspo fra : https://kotlinlang.org/docs/exceptions.html#the-finally-block
            } finally {
                _isLoading.value = false
            }
        }
    }
    //Dette er funksjonen som legger til ny anime idea i Databasen, tittel og beskrivelse av ideen.(Ved hjelp av repository)
    fun addAnimeIdea(title: String, description: String) {
        viewModelScope.launch {
            // Lager et nytt objekt med tittelen og beskrivelsen som er sent inn.
            val newIdea = AnimeIdea(
                title = title,
                description = description,
                isDeleted = false //Settes til false fordi ideen finnes.
            )
            //Legger til ideen i databsen via repository.
            val result = AnimeDbRepository.insertAnimeIdeas(newIdea)
            if (result != -1L) {
                loadAnimeIdeas() //Loader ny liste hvis lykkes.
            }
        }
    }

    //Funksjonen som oppdaterer en ide via repository
    //Sender inn et eksiterende objekt i updateAnimeIdea
    fun updateAnimeIdea(animeIdea: AnimeIdea){
        viewModelScope.launch {
            val updatedAnimeIdea = AnimeDbRepository.updateAnimeIdea(animeIdea)
            if (updatedAnimeIdea) {
                loadAnimeIdeas()
            }
        }

    }

    //Funksjonen som sletter en anime ide via repository.
    fun deleteAnimeIdea(id: Int) {
        viewModelScope.launch {
            val deletedAnimeIdea = AnimeDbRepository.deleteAnimeIdea(id)
            if (deletedAnimeIdea) {
                loadAnimeIdeas()
            }
        }
    }

}