import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import SpinWheelEvents.*
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.collectAsState
import ui.CongratsDialogComponent
import ui.SimpleButton
import ui.SpinWheelComponent
import ui.SpinWheelDataItem
import ui.SpinWheelItem
import ui.rememberSpinWheelState
import wheeloffortune.composeapp.generated.resources.Res
import wheeloffortune.composeapp.generated.resources.add_option_button_label
import wheeloffortune.composeapp.generated.resources.add_option_label
import wheeloffortune.composeapp.generated.resources.arrow_down
import wheeloffortune.composeapp.generated.resources.delete_option_button_label
import wheeloffortune.composeapp.generated.resources.logo
import wheeloffortune.composeapp.generated.resources.round_circle
import wheeloffortune.composeapp.generated.resources.spin_wheel_button_label

@Composable
fun SpinWheelScreen(
    viewModel: SpinWheelViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val spinState = rememberSpinWheelState(
        items = uiState.spinWheelItemsList.toUiComponentList(),
        backgroundImage = null,
        centerImage = Res.drawable.round_circle,
        indicatorImage = Res.drawable.arrow_down,
        onSpinningFinished = { viewModel.onEvent(WheelStoppedEvent) }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(state = scrollState)
            .padding(36.dp)
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            painter = painterResource(resource = Res.drawable.logo),
            contentDescription = null
        )

        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .size(300.dp)
        ) {
            SpinWheelComponent(spinState)
            if (uiState.isNewIndexSelected) {
                spinState.stoppingWheel(uiState.selectedItemIndex)
            }
        }
        Spacer(modifier = Modifier.size(48.dp))
        SimpleButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = uiState.isSpinButtonEnabled,
            resource = Res.string.spin_wheel_button_label,
            onClick = { viewModel.onEvent(SpinButtonClickedEvent) }
        )
        Spacer(modifier = Modifier.size(48.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.textFieldContent,
            onValueChange = { viewModel.onEvent(TextFieldUpdatedEvent(it)) },
            label = { Text(stringResource(Res.string.add_option_label)) }
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            SimpleButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                resource = Res.string.add_option_button_label,
                enabled = uiState.isAddButtonEnabled,
                onClick = { viewModel.onEvent(AddButtonClickedEvent) }
            )
            SimpleButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                resource = Res.string.delete_option_button_label,
                enabled = uiState.isDeleteButtonEnabled,
                onClick = { viewModel.onEvent(DeleteButtonClickedEvent) }
            )
        }
    }

    if (uiState.openAlertDialog) {
        CongratsDialogComponent(
            onDismissRequest = { viewModel.onEvent(AlertDismissedEvent) },
            winnerName = uiState.winner
        )
    }
}

private fun List<SpinWheelDataItem>.toUiComponentList(): List<SpinWheelItem> = this.map {
    SpinWheelItem(colors = it.colors) {
        Text(
            text = it.text,
            style = TextStyle(color = Color.White, fontSize = 20.sp)
        )
    }
}.toList()