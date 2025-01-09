package ui

import androidx.compose.ui.graphics.Color

object ColorListGenerator {

    private val colors1 = listOf(
        "ffa4c8",
        "ffb4d2",
        "fcc3da",
        "fdd1e2",
        "ffdeeb"
    )

    private val colors2 = listOf(
        "84cbeb",
        "92d2ef",
        "a3d7ef",
        "b6e0f3",
        "c9e5f2"
    )

    private val colors3 = listOf(
        "b7acd7",
        "c3bade",
        "cbc3e3",
        "dbd5eb",
        "e7e3f2"
    )

    private val colors4 = listOf(
        "b98db9",
        "c19ac1",
        "d0b3d0",
        "d8bfd8",
        "e0cce0"
    )

    fun getColorList(index: Int): List<Color> {
        val colors = if (index % 4 == 0) colors1
        else if (index % 4 == 1) colors2
        else if (index % 4 == 2) colors3
        else colors4
        return colors.map { it.toColor() }
    }

    fun String.toColor(): Color {
        return if (this.length == 6) {
            val r = this.substring(0, 2).toInt(16)
            val g = this.substring(2, 4).toInt(16)
            val b = this.substring(4, 6).toInt(16)
            Color(r, g, b)
        } else {
            Color.White
        }
    }
}
