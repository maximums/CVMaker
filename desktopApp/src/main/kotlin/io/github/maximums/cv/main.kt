@file:JvmName("Main")

package io.github.maximums.cv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.chrisjenx.compose2pdf.renderToPdf
import io.github.maximums.cv.designsystem.Inter
import io.github.maximums.cv.figma.FigmaCV
import io.github.maximums.cv.parser.model.CVConfig
import io.github.maximums.cv.parser.model.Parser
import java.io.File

fun main() = application {
    val windowsState = rememberWindowState(size = DpSize(width = 610f.dp, height = 1010f.dp))
    var data by mutableStateOf<CVConfig?>(null)
    CompositionLocalProvider(
        LocalDensity provides Density(density = 1f, fontScale = 1f)
    ) {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CV Builder",
            state = windowsState,
            resizable = false,
            alwaysOnTop = true,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HybridPickerScreen { cvPath -> data = Parser.loadFromFile(filePath = cvPath) }
//                FigmaCV()
                Button(
                    onClick = { saveToPdf { data?.let { FigmaCV(it, heightFraction = 1f) } } }
                ) {
                    Text("Save as PDF")
                }
            }
        }
    }
}

private fun saveToPdf(fileName: String = "Compose_CV_test.pdf", content: @Composable () -> Unit) {
    val pdfBytes = renderToPdf(
        defaultFontFamily = Inter
    ) {
        content()
    }

    File("build/pdfs/$fileName")
        .also {  file -> if (!file.exists()) file.parentFile.mkdirs() }
        .writeBytes(pdfBytes)
}