package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme =
    darkColorScheme(
        primary = PopGreenPrimaryDark,
        onPrimary = PopGreenOnPrimaryDark,
        primaryContainer = PopGreenContainerDark,
        onPrimaryContainer = PopGreenOnContainerDark,
        secondary = PopYellowSecondaryDark,
        onSecondary = PopYellowOnSecondaryDark,
        secondaryContainer = PopYellowContainerDark,
        onSecondaryContainer = PopYellowOnContainerDark,
        tertiary = PopVioletTertiaryDark,
        onTertiary = PopVioletOnTertiaryDark,
        tertiaryContainer = PopVioletContainerDark,
        onTertiaryContainer = PopVioletOnContainerDark,
        background = PopBackgroundDark,
        onBackground = PopOnBackgroundDark,
        surface = PopSurfaceDark,
        onSurface = PopOnSurfaceDark,
        surfaceVariant = PopSurfaceVariantDark,
        onSurfaceVariant = PopOnSurfaceVariantDark,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = PopGreenPrimary,
        onPrimary = PopGreenOnPrimary,
        primaryContainer = PopGreenContainer,
        onPrimaryContainer = PopGreenOnContainer,
        secondary = PopYellowSecondary,
        onSecondary = PopYellowOnSecondary,
        secondaryContainer = PopYellowContainer,
        onSecondaryContainer = PopYellowOnContainer,
        tertiary = PopVioletTertiary,
        onTertiary = PopVioletOnTertiary,
        tertiaryContainer = PopVioletContainer,
        onTertiaryContainer = PopVioletOnContainer,
        background = PopBackgroundLight,
        onBackground = PopOnBackgroundLight,
        surface = PopSurfaceLight,
        onSurface = PopOnSurfaceLight,
        surfaceVariant = PopSurfaceVariantLight,
        onSurfaceVariant = PopOnSurfaceVariantLight,
    )

// Bouncy, friendly, animated rounded corner shapes
val AnimatedShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
  val colorScheme =
      when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
      }

  MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      shapes = AnimatedShapes,
      content = content
  )
}
