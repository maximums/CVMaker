package io.github.maximums.cv.data

import androidx.compose.runtime.Immutable

@Immutable
data class ContactUi(
    val email: Email,
    val location: Location,
    val phone: PhoneNumber? = null,
    val websiteUrl: WebsiteUrl? = null,
)

@Immutable
@JvmInline
value class Email(val value: String)

@Immutable
@JvmInline
value class PhoneNumber(val value: String)

@Immutable
@JvmInline
value class WebsiteUrl(val value: String)
