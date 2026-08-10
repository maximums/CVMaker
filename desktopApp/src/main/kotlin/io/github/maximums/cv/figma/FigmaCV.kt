package io.github.maximums.cv.figma

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.maximums.cv.CVInfo
import io.github.maximums.cv.designsystem.icon.AppIcon
import io.github.maximums.cv.parser.model.CVConfig
import io.github.maximums.cv.parser.model.Certification
import io.github.maximums.cv.parser.model.Contacts
import io.github.maximums.cv.parser.model.Education
import io.github.maximums.cv.parser.model.Experience
import io.github.maximums.cv.parser.model.Language
import io.github.maximums.cv.parser.model.Summary
import io.github.maximums.cv.parser.model.TechSkills

@Composable
fun FigmaCV(data: CVConfig, heightFraction: Float = 0.9f /* Temp parameter, for testing */) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(heightFraction)
            .background(Color(0xFFFDFBF7))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.005f)
                    .background(Color(0xFFB86A4B))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Header(data.name, data.title, data.subtitles)
                    Body(
                        data.contacts,
                        data.education,
                        data.certifications,
                        data.languages,
                        data.summary,
                        data.skills,
                        data.experience
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier,
        thickness = 1.dp,
        color = Color(0xFFB86A4B),
    )
}

@Composable
private fun HeaderDivider(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        drawLine(
            color = Color(0xFFB86A4B),
            start = Offset(0f, size.height),
            end = Offset(20f, size.height),
            strokeWidth = 1.25f,
        )

        drawLine(
            color = Color(0xFF2B231E),
            start = Offset(40f, size.height),
            end = Offset(size.width - 40f, size.height),
            strokeWidth = .25f
        )

        drawLine(
            color = Color(0xFFB86A4B),
            start = Offset(size.width - 20f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1.25f,
        )
    }
}

@Composable
fun BulletListItem(
    text: String,
    modifier: Modifier = Modifier,
    bulletColor: Color = Color(0xFF5C524E),
    fontSize: TextUnit = 12.sp
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier,
    ) {
        Text(
            text = "\u2022",
            fontSize = fontSize,
            color = bulletColor
        )

        Text(
            text = text,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF5C524E),
            fontSize = fontSize,
        )
    }
}

@Composable
private fun Header(name: String, title: String, subtitles: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = AppIcon.KotlinIcon.painter,
                contentDescription = AppIcon.KotlinIcon.description
            )

            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B231E),
                fontSize = 24.sp
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFB86A4B),
                fontSize = 14.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            subtitles.forEach { subTitle ->
                BulletListItem(text = subTitle)
            }
        }

        HeaderDivider(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun Body(
    contacts: Contacts,
    education: List<Education>,
    certifications: List<Certification>,
    languages: List<Language>,
    summary: Summary,
    skills: TechSkills,
    experience: List<Experience>
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SideInfoBar(contacts, education, certifications, languages)
        MainContent(summary, skills, experience)
    }
}

@Composable
private fun RowScope.SideInfoBar(
    contacts: Contacts,
    education: List<Education>,
    certifications: List<Certification>,
    languages: List<Language>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.weight(.3f),
    ) {
        ContactSection(contacts)
        EducationSection(education)
        CertificationsSection(certifications)
        LanguagesSection(languages)
        NoteSection(modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun ContactSection(contacts: Contacts) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SectionTitle(text = "Contact")
        ContactTitle(text = "Email")
        Text(
            text = contacts.email,
            color = Color(0xFF2B231E),
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
        )

        Spacer(Modifier.height(2.dp))

        ContactTitle(text = "Phone")
        Text(
            text = contacts.phone,
            color = Color(0xFF2B231E),
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
        )

        Spacer(Modifier.height(2.dp))

        ContactTitle(text = "Website")
        Text(
            text = contacts.website,
            color = Color(0xFF2B231E),
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
        )

        Spacer(Modifier.height(2.dp))

        ContactTitle(text = "Location")
        Text(
            text = contacts.location,
            color = Color(0xFF2B231E),
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
        )
    }
}

@Composable
private fun EducationSection(education: List<Education>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SectionTitle(text = "Education")
        education.forEach { degree ->
            Text(
                text = degree.fieldName,
                color = Color(0xFF2B231E),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            )
            Text(
                text = degree.institution,
                color = Color(0xFF2B231E),
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
            )
        }
    }
}

@Composable
private fun CertificationsSection(certifications: List<Certification>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SectionTitle(text = "Certifications")
        certifications.forEach { certification ->
            Text(
                text = certification.name,
                color = Color(0xFF2B231E),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            )
            Text(
                text = certification.description,
                color = Color(0xFF2B231E),
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
            )
        }
    }
}

@Composable
private fun LanguagesSection(languages: List<Language>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        SectionTitle(text = "Languages")
        languages.forEach { lang ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lang.name,
                    color = Color(0xFF2B231E),
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                )
                Spacer(Modifier.weight(1f))
                repeat(lang.level) {
                    Text(
                        text = "\u2022",
                        fontSize = 16.sp,
                        color = Color(0xFFB86A4B),
                    )
                }
            }
        }
    }
}

@Composable
private fun NoteSection(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Image(
            painter = AppIcon.ComposeIcon.painter,
            contentDescription = AppIcon.ComposeIcon.description,
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Text(
                text = "Built With",
                color = Color(0xFFB86A4B),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp,
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "100% of this CV was built with Compose Multiplatform",
                color = Color(0xFF2B231E),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
            )
        }
    }
}

@Composable
private fun RowScope.MainContent(
    summary: Summary,
    skills: TechSkills,
    experience: List<Experience>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.weight(.7f)
    ) {
        SectionTitle(text = "Summary")

        Text(
            text = summary.value,
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
            color = Color(0xFF2B231E),
            modifier = Modifier.fillMaxWidth()
        )

        TechSection(skills = skills.value.toSet())

        ExpSection(experience)
    }
}

@Composable
private fun TechSection(skills: Set<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionTitle(text = "Technical Skills")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            skills.forEach { skill ->
                TechSkillItem(name = skill)
            }
        }
    }
}

@Composable
private fun ExpSection(experience: List<Experience>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        SectionTitle(text = "Experience")
        experience.forEach { exp ->
            ExperienceItem(data = exp)
        }
    }
}

@Composable
private fun ExperienceItem(data: Experience, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = data.title,
                color = Color(0xFF2B231E),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            )

            Text(
                text = data.employer,
                color = Color(0xFFB86A4B),
                fontWeight = FontWeight.Normal,
                fontSize = 9.sp,
            )
            
            Spacer(Modifier.weight(1f))

            Text(
                text = data.period,
                color = Color(0xFF2B231E),
                fontWeight = FontWeight.Normal,
                fontSize = 9.sp,
            )
        }

        Text(
            text = data.location,
            color = Color(0xFF2B231E),
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
        )

        Spacer(Modifier.height(4.dp))

        data.tasks.forEach { task ->
            BulletListItem(
                text = task,
                fontSize = 9.sp,
                bulletColor = Color(0xFFB86A4B)
            )
        }
    }
}

@Composable
private fun TechSkillItem(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        color = Color(0xFF2B231E),
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        modifier = modifier
            .background(color = Color(0xFFF5EDE4))
            .padding(vertical = 2.dp, horizontal = 4.dp)
    )
}

@Composable
private fun ContactTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Color(0xFFB86A4B),
        fontWeight = FontWeight.SemiBold,
        fontSize = 9.sp,
        modifier = modifier,
    )
}

@Composable
private fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF2B231E),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
        )

        SectionDivider()
    }
}