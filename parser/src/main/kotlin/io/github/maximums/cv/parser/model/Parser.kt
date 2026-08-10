package io.github.maximums.cv.parser.model

import com.akuleshov7.ktoml.Toml
import kotlinx.serialization.decodeFromString
import java.nio.file.Path

object Parser {
    fun loadFromFile(filePath: Path): CVConfig {
        val content = filePath.toFile().readText()
        return Toml.decodeFromString<CVConfig>(content)
    }
}
