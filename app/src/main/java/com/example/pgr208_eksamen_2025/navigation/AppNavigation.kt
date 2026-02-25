package com.example.pgr208_eksamen_2025.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShuffleOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Shuffle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pgr208_eksamen_2025.screens.animeHome.AnimeHomeScreen
import com.example.pgr208_eksamen_2025.screens.animeIdeas.AnimeIdeasScreen
import com.example.pgr208_eksamen_2025.screens.animeIdeas.AnimeIdeasViewModel
import com.example.pgr208_eksamen_2025.screens.animeRandom.AnimeRandomScreen
import com.example.pgr208_eksamen_2025.screens.animeSearch.AnimeSearchScreen

// ===APP NAVIGATION===

// Definerer navigasjonsstrukturen og bottom bar for hele appen.

@Composable
fun AppNavigation() {


    // ===NAV CONTROLLER===
    // Styrer hvilke skjermer som vises
    val navController = rememberNavController()


    // ===BOTTOM BAR STATE===
    // Styr på hvilken knapp som er valgt
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(1) // 1 = skjerm en
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        // ===BOTTOM NAVIGATION BAR===
        bottomBar = {
            NavigationBar(
                contentColor = Color(0xFF8667A3),
                containerColor = Color(0xFF122640)
            ) {

                // Fargeopsett for alle navigation items
                val navItemColors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF8667A3),
                    selectedTextColor = Color(0xFF8667A3),

                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,

                    indicatorColor = Color(0xFF1F3B60)
                )

                // ===HOME TAB===
                NavigationBarItem(
                    selected = selectedItemIndex == 1,
                    onClick = {
                        selectedItemIndex = 1
                        navController.navigate(Routes.Home)
                    },

                    icon = {
                        if (selectedItemIndex == 1) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Home,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text("Home")
                    },
                    colors = navItemColors
                ) // End Home


                // ===SEARCH TAB===
                NavigationBarItem(
                    selected = selectedItemIndex == 2,
                    onClick = {
                        selectedItemIndex = 2
                        navController.navigate(Routes.Search)
                    },
                    icon = {
                        if (selectedItemIndex == 2) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text("Search")
                    },
                    colors = navItemColors
                ) // End Search


                // ===IDEAS TAB===
                NavigationBarItem(
                    selected = selectedItemIndex == 3,
                    onClick = {
                        selectedItemIndex = 3
                        navController.navigate(Routes.Ideas)
                    },
                    icon = {
                        if (selectedItemIndex == 3) {
                            Icon(
                                imageVector = Icons.Filled.Lightbulb,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Lightbulb,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text("Ideas")
                    },
                    colors = navItemColors
                ) // End Ideas


                // ===RANDOM TAB===
                NavigationBarItem(
                    selected = selectedItemIndex == 4,
                    onClick = {
                        selectedItemIndex = 4
                        navController.navigate(Routes.Random)
                    },
                    icon = {
                        if (selectedItemIndex == 4) {
                            Icon(
                                imageVector = Icons.Filled.ShuffleOn,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Shuffle,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text("Random")
                    },
                    colors = navItemColors
                ) // End Random
            }
        }
    ) { innerPadding ->

        // ===SKJERM INNHOLD (NAVHOST)
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

            //Routing systemet
            NavHost(
                navController = navController,
                startDestination = Routes.Home
            ) {
                composable<Routes.Home> {
                    AnimeHomeScreen()
                }
                composable<Routes.Search> {
                    AnimeSearchScreen()
                }
                composable<Routes.Ideas> {
                    val viewModel: AnimeIdeasViewModel = viewModel()
                    AnimeIdeasScreen(viewModel)
                }
                composable<Routes.Random> {
                    AnimeRandomScreen()
                }
            }
        }
    }
}
