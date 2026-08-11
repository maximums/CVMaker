package io.github.maximums.cv.parser.model

import kotlinx.serialization.Serializable

@Serializable
data class Education(
    val fieldName: String,
    val institution: String,
)
