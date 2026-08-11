package io.github.maximums.cv.data

import androidx.compose.runtime.Immutable

@Immutable
data class CVDataUi(
    val name: String,
    val title: String,
    val subtitles: Set<String> = emptySet(),
    val summary: String,
    val techSkills: Set<String>,
    val languages: Set<LanguageUi>,
    val contacts: ContactUi? = null,
    val education: Set<EducationUi>? = null, // Let's say it is nullable not because you're illiterate, but because you don't want to include it in the CV
    val certifications: List<CertificationUi>? = null,
    val experience: Set<ExperienceUi>? = null, // Nullable because the world is cruel xD
)
