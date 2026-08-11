package io.github.maximums.cv

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.maximums.cv.parser.model.CVDataFormat
import java.io.File
import java.nio.file.Path
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter

private val defaultFilter = object : FileFilter() {
    override fun accept(file: File) = when {
        file.isDirectory -> false
        else -> CVDataFormat.extensions.any { ext -> file.name.endsWith(suffix = ext, ignoreCase = true) }
    }

    override fun getDescription() = "Json or Toml files"
}

@Composable
fun FilePickerDialog(filter: FileFilter = defaultFilter, onClick: (Path) -> Unit) {
    val chooser = remember {
        JFileChooser().apply {
            fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
            dialogTitle = "Select a CV file in Json/Toml format"
            fileFilter = filter
        }
    }

    Button(
        onClick = {
            when {
                chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION -> onClick(chooser.selectedFile.toPath())
                else -> Unit
            }
        }
    ) {
        Text("Select CV in Json/Toml format")
    }
}
