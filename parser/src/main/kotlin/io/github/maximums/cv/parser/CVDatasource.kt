package io.github.maximums.cv.parser

import com.akuleshov7.ktoml.Toml
import io.github.maximums.cv.parser.model.CVData
import io.github.maximums.cv.parser.model.CVDataFormat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.nio.file.Path
import kotlin.io.path.extension

class CVDatasource(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun loadData(filePath: Path): Result<CVData> {
        val textData = withContext(ioDispatcher) {
            runCatching { filePath.toFile().readText() }
        }

        return textData.map { text -> filePath.format.decodeFromString<CVData>(text) }
    }

    private val Path.format: StringFormat
        get() = when (CVDataFormat.valueOf(extension)) {
            CVDataFormat.JSON -> Json
            CVDataFormat.TOML -> Toml
        }
}
