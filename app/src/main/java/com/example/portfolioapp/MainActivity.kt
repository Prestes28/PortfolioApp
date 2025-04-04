package com.example.portfolioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.portfolioapp.ui.screens.LoginScreen
import com.example.portfolioapp.ui.screens.PortfolioScreen
import com.example.portfolioapp.ui.theme.PortfolioAppTheme

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioAppTheme {
                var isLoggedIn by remember { mutableStateOf(false) }
                val density = LocalDensity.current

                AnimatedContent(
                    targetState = isLoggedIn,
                    transitionSpec = {
                        if (targetState) {
                            slideInHorizontally(
                                initialOffsetX = { with(density) { 300.dp.roundToPx() } },
                                animationSpec = tween(
                                    durationMillis = 700,
                                    easing = FastOutSlowInEasing
                                )
                            ) + fadeIn(
                                animationSpec = tween(500)
                            ) togetherWith
                            slideOutHorizontally(
                                targetOffsetX = { with(density) { (-300).dp.roundToPx() } },
                                animationSpec = tween(
                                    durationMillis = 700,
                                    easing = FastOutSlowInEasing
                                )
                            ) + fadeOut(
                                animationSpec = tween(500)
                            )
                        } else {
                            slideInHorizontally(
                                initialOffsetX = { with(density) { (-300).dp.roundToPx() } },
                                animationSpec = tween(
                                    durationMillis = 700,
                                    easing = FastOutSlowInEasing
                                )
                            ) + fadeIn(
                                animationSpec = tween(500)
                            ) togetherWith
                            slideOutHorizontally(
                                targetOffsetX = { with(density) { 300.dp.roundToPx() } },
                                animationSpec = tween(
                                    durationMillis = 700,
                                    easing = FastOutSlowInEasing
                                )
                            ) + fadeOut(
                                animationSpec = tween(500)
                            )
                        }
                    },
                    label = "screen_transition"
                ) { targetState ->
                    if (targetState) {
                        PortfolioScreen(
                            onLogout = {
                                isLoggedIn = false
                            }
                        )
                    } else {
                        LoginScreen(
                            onLoginSuccess = {
                                isLoggedIn = true
                            }
                        )
                    }
                }
            }
        }
    }
}