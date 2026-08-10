package io.github.maximums.cv.parser.model

import kotlinx.serialization.Serializable

@Serializable
data class CVConfig(
    val name: String,
    val title: String,
    val subtitles: List<String> = emptyList(),
    val contacts: Contacts,
    val education: List<Education>,
    val certifications: List<Certification>,
    val languages: List<Language>,
    val summary: Summary,
    val skills: TechSkills,
    val experience: List<Experience>,
)

@Serializable
data class Contacts(
    val phone: String,
    val email: String,
    val website: String,
    val location: String,
)

@Serializable
data class Education(
    val fieldName: String,
    val institution: String,
)

@Serializable
data class Certification(
    val name: String,
    val description: String,
)

@Serializable
data class Language(
    val name: String,
    val level: Int,
)

@Serializable
data class Summary(val value: String)

@Serializable
data class TechSkills(val value: List<String>)

@Serializable
data class Experience(
    val title: String,
    val employer: String,
    val period: String,
    val location: String,
    val tasks: List<String>,
)
