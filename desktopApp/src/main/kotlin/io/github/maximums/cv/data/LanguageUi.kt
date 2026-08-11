package io.github.maximums.cv.data

import androidx.annotation.IntRange
import androidx.compose.runtime.Immutable

@Immutable
data class LanguageUi(
    val name: String,
    @IntRange(from = 0, to = 5) val level: Int,
    val label: LangLabel
)

@Immutable
@JvmInline
value class LangLabel(val value: String)
