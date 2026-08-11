package io.github.maximums.cv.parser.model

import kotlinx.serialization.Serializable

@Serializable
data class Contacts(
    val phone: String,
    val email: String,
    val website: String,
    val location: String,
)
