package ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class SpinWheelDataItem(
    val colors: List<Color>,
    val text: String
)

data class SpinWheelItem(
    val colors: List<Color>,
    val content: @Composable () -> Unit,
)

fun List<SpinWheelItem>.getDegreesPerItem(): Float {
    return (360f / this.size.toFloat())
}
