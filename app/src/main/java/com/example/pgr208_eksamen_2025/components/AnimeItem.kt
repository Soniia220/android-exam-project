package com.example.pgr208_eksamen_2025.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel


// ===Home ITEM===

// Viser en anime som et kort med bilde, tittel, score og episodeinfo.
@Composable
fun AnimeItem(
    anime: AnimeApiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) {

        // ===BILDE + BADGE===
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        ) {

            // Bilde
            AsyncImage(
                model = anime.images.jpg.image_url,
                contentDescription = anime.title,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            // ===TOP BADGE===
            if ((anime.score ?: 0.0) > 8.0) {
                Text(
                    text = "TOP",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Yellow)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ===TEKST===
        Text(
            text = anime.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontSize = 18.sp
        )

        Text(
            text = "Score: ${anime.score ?: "N/A"}",
            color = Color.LightGray,
            fontSize = 14.sp
        )

        Text(
            text = "Episodes: ${anime.episodes ?: "Ukjent"}",
            color = Color.LightGray,
            fontSize = 14.sp
        )
    }
}

