package io.github.maximums.cv.data

sealed interface AppState {
    data object Initial : AppState

    data object Loading : AppState

    @JvmInline value class Loaded(val data: CVDataUi) : AppState
}
