package com.example.pgr208_eksamen_2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pgr208_eksamen_2025.data.Room.AnimeDbRepository
import com.example.pgr208_eksamen_2025.navigation.AppNavigation
import com.example.pgr208_eksamen_2025.ui.theme.PGR208_Eksamen_2025Theme

// HUSK Å IKKE HA GJENTAKENDE KALL, sjekk tilbakemleding fra eksamen.
// Ha en helt unik løsning i skjerm 4, ikke ha to søkefelt
// ===MAIN ACTIIVTY===
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


// Initialiserer databasen via repository. Dette må gjøres før vi prøver å bruke den.
        AnimeDbRepository.initializeDB(applicationContext)

        enableEdgeToEdge()
        setContent {
            PGR208_Eksamen_2025Theme {
                AppNavigation()
                }
            }
        }
    }
