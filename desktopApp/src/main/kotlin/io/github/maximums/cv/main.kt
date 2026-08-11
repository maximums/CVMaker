@file:JvmName("Main")

package io.github.maximums.cv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.chrisjenx.compose2pdf.renderToPdf
import io.github.maximums.cv.designsystem.Inter
import io.github.maximums.cv.ui.CV
import io.github.maximums.cv.parser.model.CVData
import java.io.File

fun main() = application {
    var data by mutableStateOf<CVData?>(null)
    Window(onCloseRequest = ::exitApplication, title = "CV Builder") {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//                FigmaCV()
            Button(
                onClick = { saveToPdf { data?.let { CV(it, heightFraction = 1f) } } }
            ) {
                Text("Save as PDF")
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