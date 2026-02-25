package com.example.pgr208_eksamen_2025.screens.animeIdeas


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pgr208_eksamen_2025.components.AppBackground
import com.example.pgr208_eksamen_2025.components.AppHeader
import com.example.pgr208_eksamen_2025.data.Room.AnimeIdea

@Composable
fun AnimeIdeasScreen(
    animeIdeasViewModel: AnimeIdeasViewModel

) {
    val animeIdeas by animeIdeasViewModel.animeIdeas.collectAsState()
    val isLoading by animeIdeasViewModel.isLoading.collectAsState()
    //State for inputfeltene
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var editingId by remember { mutableStateOf<Int?>(null) }//Hvilken av ide som skal redigeres, null hvis ingen

    AppBackground {
        AppHeader(title = "MyAnime")


        // ===Main===
        Column(modifier = Modifier.padding(
            //Slik at det blir mellomrom mellom kanten på skjermen og innhold
            start = 10.dp,
            top = 10.dp,
            end = 10.dp


        ).fillMaxSize()
        ) {


            // ===INPUT FELTER===

            // Input felt for tittel
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF00E5FF),

                    unfocusedIndicatorColor = Color(0xFFB800FF)
                )

            )
            // Input felt for beskrivelse
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                singleLine = false, //slik at brukeren kan skrive i flere linjer
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF00E5FF),

                    unfocusedIndicatorColor = Color(0xFFB800FF)
                )


            )

            //===ONCLICK LOGIKK===

            // Disse knappene har ansvar for 책 enten legge til eller oppdatere ide.
            Button(
                onClick = {
                    // Hindrer tomme innsendinger.
                    if (title.isNotBlank() && description.isNotBlank()) {
                        if (editingId != null) {
                            // Oppdater den eksiterende ideen via Viewmodel
                            animeIdeasViewModel.updateAnimeIdea(
                                AnimeIdea(
                                    id = editingId!!,
                                    title = title,
                                    description = description
                                )
                            )
                            editingId = null //Avslutter redigeringen.
                        } else {
                            // Legger til den nye ideen
                            animeIdeasViewModel.addAnimeIdea(title, description)
                        }
                        title = ""
                        description = ""
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) { // Endre knapper basert p책 om bruker er i redigeringsmodus eller ikke
                Text(if (editingId != null) "Update idea" else "Add idea")
            }


            //=== LIST INNHOLD===
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                // Styler header.
                Text(
                    text = "My Anime Ideas",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 26.dp),
                    color = Color(0xFFC40CEB)
                )

                //===TILEGGSFUNKSJONER===
                //Teller og viser brukeren kun de synlige ideene.
                val visibleIdeas = animeIdeas.filter {it.title.isNotBlank() && it.description.isNotBlank()}

                Text(
                    text = "Total Anime Ideas: ${visibleIdeas.size}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // H책ndterer loading og tomme tilstander.
                if (isLoading) {
                    CircularProgressIndicator()
                } else if (animeIdeas.isEmpty()) {
                    // Tom tilstand:
                    Text("No anime ideas yet. Add some ideas to see them here!",
                        color = Color.White)
                } else {
                    // ===LISTE AV IDEER===
                    LazyColumn {
                        // Filtrere ut tomme title eller description.
                        items(animeIdeas.filter { it.title.isNotBlank() && it.description.isNotBlank() }) { idea ->

                            Card(
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .fillMaxWidth()
                                    .border(1.dp, Color.Transparent)
                                    .shadow(4.dp, RoundedCornerShape(10.dp))

                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    //Tittel
                                    Text(
                                        text = idea.title,
                                        style = MaterialTheme.typography.headlineSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                    //Beskrivelse
                                    Text(
                                        text = idea.description,
                                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                        maxLines = 3
                                    )


                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically


                                    ) {
                                        //Knapper
                                        // Oppdater knapp
                                        IconButton(onClick = {
                                            title = idea.title
                                            description = idea.description
                                            editingId = idea.id
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Update"
                                            )
                                        }
                                        // Slett knapp som triggrer Viewmodel delete.
                                        IconButton(onClick = {
                                            animeIdeasViewModel.deleteAnimeIdea(
                                                idea.id
                                            )
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete",
                                                tint = Color(0xFFE57373)
                                            )
                                        }
                                    }


                                }


                            }
                        }
                    }
                }
            }
        }
    }
}