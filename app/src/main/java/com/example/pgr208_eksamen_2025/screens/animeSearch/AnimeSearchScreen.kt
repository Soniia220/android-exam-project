package com.example.pgr208_eksamen_2025.screens.animeSearch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pgr208_eksamen_2025.components.AnimeItem
import com.example.pgr208_eksamen_2025.components.AppBackground
import com.example.pgr208_eksamen_2025.components.AppHeader
import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel



// ===SEARCH SCREEN===

// Lar brukeren søke etter en anime basert på id
@Composable
fun AnimeSearchScreen(
    viewModel: AnimeSearchViewModel = viewModel()
) {
    // State for søk
    val searchId by viewModel.searchId
    val result by viewModel.animeResult
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    AppBackground {
        AppHeader(title = "Find Anime")



    Column(
        modifier = Modifier.padding(
        ).fillMaxSize()
    ){
        // ===INPUT===
        OutlinedTextField(
            value = searchId,
            onValueChange = { viewModel.searchId.value = it },
            label = { Text("Anime id") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(focusedIndicatorColor = Color(0xFF00E5FF),
                unfocusedIndicatorColor = Color(0xFFB800FF)  )
        )

        // ===KNAPP FOR SØK===
        Button(
            onClick = { viewModel.search() },
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text("Søk")
        }

        // ===RESULTATVISNING===
        when {
            isLoading -> Text(text = "Laster anime…", modifier = Modifier.padding(top = 16.dp))
            errorMessage != null -> Text(
                text = "Feil: $errorMessage", color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )
            result != null -> AnimeSearchResult(anime = result!!)
        }
    }
}
}

@Composable
private fun AnimeSearchResult(anime: AnimeApiModel) {
   Column{
       AnimeItem(anime = anime)
   }
    }
