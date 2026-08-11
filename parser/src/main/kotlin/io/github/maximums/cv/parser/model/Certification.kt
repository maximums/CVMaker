package io.github.maximums.cv.parser.model

import kotlinx.serialization.Serializable

@Serializable
data class Certification(
    val name: String,
    val description: String,
)
