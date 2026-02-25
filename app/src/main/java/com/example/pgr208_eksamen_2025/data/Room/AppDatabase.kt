package com.example.pgr208_eksamen_2025.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase



// AppDatabase er klassen som representerer room database.
@Database(
    entities = [AnimeIdea::class],
    version = 1 // Versjonen vi er på. Økes med +1 hvis vi endrer på noe.

)
abstract class AppDatabase : RoomDatabase(){
    //Gir oss DAO, som skal brukes for å lagre hente og slette data.
    abstract fun getAnimeDao() : AnimeDao

}