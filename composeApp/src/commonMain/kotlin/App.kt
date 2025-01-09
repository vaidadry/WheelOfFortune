@file:OptIn(KoinExperimentalAPI::class)

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun App() {
    MaterialTheme {
        KoinContext {
            val viewModel = koinViewModel<SpinWheelViewModel>()
            SpinWheelScreen(viewModel)
        }
    }
}
