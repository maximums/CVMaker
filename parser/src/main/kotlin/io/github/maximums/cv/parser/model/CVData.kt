package io.github.maximums.cv.parser.model

import kotlinx.serialization.Serializable

@Serializable
data class CVData(
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
