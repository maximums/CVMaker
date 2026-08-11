package io.github.maximums.cv.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.Density
import io.github.maximums.cv.designsystem.model.AppDimension

val Inter = FontFamily(
    Font(
        resource = "composeResources/font/inter_18pt_regular.ttf",
        weight = FontWeight.Normal
    ),
)

@Composable
fun CVTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalDensity provides Density(density = 1f, fontScale = 1f),
        LocalAppDimensions provides AppDimension()
    ) {
        content()
    }
}

object CVTheme {
    val dimensions: AppDimension
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimensions.current
}

internal val LocalAppDimensions = staticCompositionLocalOf<AppDimension> { error("no app dimensions specified") }
internal val LocalAppTypography = staticCompositionLocalOf { error("no typography specified") } //TODO actually implement it
