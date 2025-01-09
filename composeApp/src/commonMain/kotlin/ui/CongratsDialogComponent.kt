package ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import wheeloffortune.composeapp.generated.resources.Res
import wheeloffortune.composeapp.generated.resources.congrats_dialog_button_label
import wheeloffortune.composeapp.generated.resources.congrats_dialog_text_label
import wheeloffortune.composeapp.generated.resources.congrats_dialog_title_label

@Composable
fun CongratsDialogComponent(
    onDismissRequest: () -> Unit,
    winnerName: String
) {
    AlertDialog(
        title = {
            Text(text = stringResource(Res.string. congrats_dialog_title_label))
        },
        text = {
            Text(text = stringResource(Res.string. congrats_dialog_text_label, winnerName))
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onDismissRequest() } ) {
                Text(text = stringResource(Res.string.congrats_dialog_button_label))
            }
        },
    )
}