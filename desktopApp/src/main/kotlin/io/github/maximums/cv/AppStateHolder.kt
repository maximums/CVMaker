package io.github.maximums.cv

import io.github.maximums.cv.data.AppState
import io.github.maximums.cv.parser.CVDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.nio.file.Path

class AppStateHolder(
    private val scope: CoroutineScope,
    private val datasource: CVDatasource,
) {

    val uiState: StateFlow<AppState>
        field = MutableStateFlow<AppState>(value = AppState.Initial)

    fun loadCVDataFile(path: Path) {
        uiState.value = AppState.Loading

        scope.launch {
            datasource.loadData(filePath = path)
                .onSuccess { data -> uiState.value = AppState.Loaded(data = data) }
                .onFailure { thr -> error(thr.message.orEmpty()) } // TODO for now
        }
    }
}
