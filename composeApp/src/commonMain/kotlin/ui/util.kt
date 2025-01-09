package ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun List<Color>.toBrush(endY: Float): Brush =
    if (this.size == 1) {
        Brush.verticalGradient(colors = this)
    } else {
        val colorStops = this.mapIndexed { index, color ->
            val stop = if (index == 0) 0f else (index.toFloat() + 1f) / this.size.toFloat()
            Pair(stop, color)
        }.toTypedArray()
        Brush.verticalGradient(
            colorStops = colorStops,
            endY = endY,
        )
    }
