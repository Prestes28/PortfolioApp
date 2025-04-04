package com.example.portfolioapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ThemeState(
    val isDarkMode: Boolean = false
)

object ThemeManager {
    var themeState by mutableStateOf(ThemeState())
        private set

    fun toggleTheme() {
        themeState = themeState.copy(isDarkMode = !themeState.isDarkMode)
    }
}

val LocalThemeState = staticCompositionLocalOf { ThemeState() } 