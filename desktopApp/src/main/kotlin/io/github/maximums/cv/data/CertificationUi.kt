package io.github.maximums.cv.data

import androidx.compose.runtime.Immutable

@Immutable
data class CertificationUi(
    val name: Name,
    val description: Description
)

@Immutable
@JvmInline
value class Name(val value: String)

@Immutable
@JvmInline
value class Description(val value: String)
