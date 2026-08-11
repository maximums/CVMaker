package io.github.maximums.cv.parser.model

enum class CVDataFormat(private val fileExt: String) {
    JSON("json"),
    TOML("TOML");

    companion object {
        val extensions get() = entries.map { entry -> ".${entry.fileExt}"}
    }
}
