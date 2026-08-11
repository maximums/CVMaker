package io.github.maximums.cv.data

import androidx.compose.runtime.Immutable

@Immutable
data class ExperienceUi(
    val title: JobTitle,
    val employer: Employer,
    val jobLocation: Location, // Remote | On-site, if on-site then where?
    val duration: Period, // For easier implementation for now it is only a string instead of concrete date
)

@Immutable
@JvmInline
value class JobTitle(val value: String)

@Immutable
@JvmInline
value class Employer(val name: String)

@Immutable
@JvmInline
value class Period(val value: String)
