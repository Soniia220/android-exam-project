package com.example.pgr208_eksamen_2025.screens.animeRandom


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pgr208_eksamen_2025.components.AnimeItem
import com.example.pgr208_eksamen_2025.components.AppBackground
import com.example.pgr208_eksamen_2025.components.AppHeader
import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel


// ===RANDOM SCREEN===

// Henter en tilfeldig anime fra API


@Composable
fun AnimeRandomScreen(
    viewModel: AnimeRandomViewModel = viewModel()
) {
    val anime: AnimeApiModel? = viewModel.randomAnime.value
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxWidth()
               .fillMaxSize()
               .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ){

            AppHeader(title = "Random anime")


        // Random knapp
            Button(
                onClick = { viewModel.generateAnime() },
                modifier = Modifier.padding(top = 16.dp)
                    .height(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Shuffle,
                    contentDescription = null
                )
                Text(text = "Generate Anime")
            }

            // Litt avstand
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 24.dp))

            // === RESULTATVISNING===
            when {
                isLoading -> Text(text = "Laster random anime...")
                errorMessage != null -> Text(text = "Feil: $errorMessage")
                anime != null -> AnimeItem(anime = anime)
            }
        }
    }
}