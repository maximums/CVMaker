package io.github.maximums.cv.data

import androidx.compose.runtime.Immutable

@Immutable
data class EducationUi(
    val degree: Degree,
    val institution: University
)

@Immutable
@JvmInline
value class University(val name: String)

@Immutable
@JvmInline
value class Degree(val value: String)
