package io.github.maximums.cv.parser.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val name: String,
    val level: Int,
)
