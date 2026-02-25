package com.example.pgr208_eksamen_2025.screens.animeHome

import AnimeHomeList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pgr208_eksamen_2025.components.AppBackground
import com.example.pgr208_eksamen_2025.components.AppHeader
import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel

// ===HOME SCREEN===

// Viser listen med anime og håndterer loading/error-state fra ViewModel


@Composable
fun AnimeHomeScreen(
    viewModel: AnimeHomeViewModel = viewModel()
) {

    // ===STATE FRA VIEWMODEL===
    val animeList: List<AnimeApiModel> = viewModel.animeList.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    AppBackground {
        Column(
            modifier = Modifier,

        ) {

            // TOPPHEADER
            AppHeader(title = "AnimeNova")

            Spacer(modifier = Modifier.height(8.dp))

            // INNHOLDET UNDER HEADEREN
            when {
                isLoading -> Text(
                    text = "Laster anime...",
                )

                errorMessage != null -> Text(
                    text = "Feil: $errorMessage", color = Color.White
                )

                animeList.isEmpty() -> Text(
                    text = "Ingen anime hentet.", color = Color.White
                )

                else -> AnimeHomeList(animeList = animeList)
            }
        }
    }
}
