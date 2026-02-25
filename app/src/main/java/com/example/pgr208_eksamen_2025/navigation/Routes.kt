package com.example.pgr208_eksamen_2025.navigation

import kotlinx.serialization.Serializable

// ===APP ROUTES===

// Definerer navigasjonsruter som bruker av NavHost

@Serializable
sealed class Routes {
    @Serializable
    object Home : Routes()

    @Serializable
    object Search : Routes()

    @Serializable
    object Ideas : Routes()

    @Serializable
    object Random : Routes()
}