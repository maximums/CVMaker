package io.github.maximums.cv.parser.model

import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    val title: String,
    val employer: String,
    val period: String,
    val location: String,
    val tasks: List<String>,
)
