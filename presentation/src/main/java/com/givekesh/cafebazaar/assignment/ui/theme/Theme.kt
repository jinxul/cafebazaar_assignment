package com.givekesh.cafebazaar.assignment.ui.theme

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = colorTitle,
    secondary = colorItemTitle,
    tertiary = Color.White,
    background = colorBackground,
)

private val LightColorScheme = lightColorScheme(
    primary = colorTitle,
    secondary = colorItemTitleLight,
    tertiary = Color.Black,
    background = colorBackgroundLight

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CafebazaarAssignmentTheme(
    content: @Composable () -> Unit
) {
    Log.e("TAG", "${isSystemInDarkTheme()}")
    val colorScheme = when (isSystemInDarkTheme()) {
        true -> DarkColorScheme
        false -> LightColorScheme
    }
    Log.e("TAG", "${colorScheme.background}")
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}