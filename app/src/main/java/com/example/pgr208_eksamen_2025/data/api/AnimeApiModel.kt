package com.example.pgr208_eksamen_2025.data.api


// ===Api modell===

// Dataklassen representerer strukturen på JSON-feltene som kommer fra Anime API-et.
// Retrofit bruker disse klassene for å mappe JSON direkte til kotilin-objekter.



// ===Mal for en Anime fra API-et===
data class AnimeApiModel(
    val mal_id: Int,
    val type: String,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: AnimeImages
)


// ===Bilde Objekt===

// Pakker bildeformatene som API-et tilbyr
data class AnimeImages(
    val jpg: AnimeImage
)

// Selve URL-en til anime-bildet
data class AnimeImage(
    val image_url: String
)