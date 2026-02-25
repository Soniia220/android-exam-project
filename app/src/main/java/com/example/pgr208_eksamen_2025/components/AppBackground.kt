package com.example.pgr208_eksamen_2025.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

// === App bakgrunn ===

// Komponenten gir felles bakgrunnsfarge til alle skjermer.

@Composable
fun AppBackground (content: @Composable () -> Unit) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color (0xFF081526))
  ){
    // Innholdet fra skjermene settes inni bakgrunn
    content()
  }
}