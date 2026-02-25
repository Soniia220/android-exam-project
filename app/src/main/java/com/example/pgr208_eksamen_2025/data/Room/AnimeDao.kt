package com.example.pgr208_eksamen_2025.data.Room



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update




//Inspo av forelesning 10 rolando.
@Dao
interface AnimeDao {
    //CRUD METODE

    //Gjør at brukeren kan legge til Anime ideer.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //Returnere en LONG som representerer den nye ID til det nye objektet
    //Denne ID kan også brukes som en bekreftelse på insert.
    //Vi ønsker å bruke denne verdien for å sjekke om det har gått bra
    suspend fun insertAnimeIdea(newAnimeIdea: AnimeIdea) : Long


    //Oppdaterer en eksiterende ide
    @Update
    suspend fun updateAnimeIdeas(animeIdea: AnimeIdea)

    // Henter alle Anime ideene brukeren har lagd som ikke er slettet.
    @Query("SELECT * FROM anime_ideas WHERE isDeleted = 0")
    suspend fun getAllAnimeIdeas() : List<AnimeIdea>


    ////Oppdaterer raden med riktig ID og setter isdeleted til 1, slik at vi kan se hva som er "soft deleted"
    // Ideen blir ikke fjernet fra databasen men kun markeres.
    @Query("UPDATE anime_ideas SET isDeleted =1 WHERE id= :id")
    suspend fun deleteAnimeIdeas(id: Int)

}

