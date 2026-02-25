package com.example.pgr208_eksamen_2025.data.Room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


// Definerer hvordan en tabell skal være.
//Sørger for a title er unik, slik at det ikke kan lagres to ideer med samme tittel.
@Entity(tableName = "anime_ideas", indices =[Index(value = ["title"], unique = true)] )
data class AnimeIdea(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Autogenerert ID, hver rad får unik nøkkel.
    var title: String, // Tittel
    var description: String, // Beskrivelse
    var isDeleted: Boolean = false // Dette brukes dersom noe "slettes", blir ikke raden fysisk fjernet.
)