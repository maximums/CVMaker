package io.github.maximums.cv.designsystem.icon


import androidx.compose.runtime.Composable
import io.github.maximums.cv.designsystem.resources.Res
import io.github.maximums.cv.designsystem.resources.compose_icon_description
import io.github.maximums.cv.designsystem.resources.compose_multiplatform
import io.github.maximums.cv.designsystem.resources.kotlin_icon
import io.github.maximums.cv.designsystem.resources.kotlin_icon_description
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

enum class AppIcon(
    private val icon: DrawableResource,
    private val contentDescription: StringResource? = null,
) {
    KotlinIcon(icon = Res.drawable.kotlin_icon, contentDescription = Res.string.kotlin_icon_description),
    ComposeIcon(icon = Res.drawable.compose_multiplatform, contentDescription = Res.string.compose_icon_description);

    val painter
        @Composable
        get() = painterResource(icon)

    val description: String?
        @Composable
        get() = contentDescription?.let { stringResource(it) }
}
