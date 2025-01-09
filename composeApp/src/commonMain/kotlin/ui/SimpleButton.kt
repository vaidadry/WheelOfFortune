package ui

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import ui.ColorListGenerator.toColor

@Composable
fun SimpleButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    resource: StringResource,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = "84cbeb".toColor(),
            contentColor = Color.White
        ),
        enabled = enabled,
        onClick = { onClick() }) {
        Text(text = stringResource(resource))
    }
}