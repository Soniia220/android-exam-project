package com.example.pgr208_eksamen_2025.data.Room

import android.content.Context
import android.util.Log
import androidx.room.Room

object AnimeDbRepository {

    private lateinit var _appDatabase: AppDatabase
    private lateinit var _animeDao: AnimeDao

    // Kjøres med en gang appen starter.
    // Henter DAO.
    fun initializeDB(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "animeideas_database"
        )
            .build()
        // Henter ut Dao fra DB, slik at vi kan utføre spørringer
        // Henter Dao etter at databasen er ferdig intialisert.
        _animeDao = _appDatabase.getAnimeDao()

    }

    // Her hentes alle ideene fra databasen, og logger dersom noe går galt.
    suspend fun getAllAnimeIdeas(): List<AnimeIdea> {
        try {
            Log.d("getAllAnimeIdeasTry", "Fikk hentet en Anime Idea")

            return _animeDao.getAllAnimeIdeas()

        } catch (e: Exception) {
            Log.d("getAllAnimeIdeasCatch", e.toString())
            return emptyList()
        }

    }
    // Legger til ny anime ide, og retunerer -1l (long), hvis noe går galt.
    suspend fun insertAnimeIdeas(newAnimeIdea: AnimeIdea): Long {
        try {
            return _animeDao.insertAnimeIdea(newAnimeIdea)
        } catch (e: Exception) {
            return -1L

        }

    }

    // funksjon som oppdaterer en ide. Returnerer en true eller false om det lykkes.
    suspend fun updateAnimeIdea(animeIdea: AnimeIdea): Boolean {
        return try {
            _animeDao.updateAnimeIdeas(animeIdea)
            true
        } catch (e: Exception) {
            Log.d("updateAnimeIdeaCatch", e.toString())
            false
        }
    }


    // Ber dao om å slette ideen med gitt id, men selve slettingen styres av DAO
    suspend fun deleteAnimeIdea(id: Int): Boolean {
        return try {
            _animeDao.deleteAnimeIdeas(id)
            true
        } catch (e: Exception) {
            Log.d("deletedAnimeIdeaCatch", e.toString())
            false
        }
    }

}