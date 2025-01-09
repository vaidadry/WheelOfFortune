import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ui.SpinWheelDataItem

interface UiState
interface UiEvent

interface Model<S : UiState, E : UiEvent> {
    val state: MutableStateFlow<S>
    val uiState: StateFlow<S>
    val events: Channel<E>
}

data class SpinWheelState(
    val spinWheelItemsList: List<SpinWheelDataItem> = emptyList(),
    val selectedItemIndex: Int = 0,
    val textFieldContent: String = "",
    val isNewIndexSelected: Boolean = false,
    val isSpinWheelSpinning: Boolean = false,
    val winner: String = "",
    val previouslySelectedItem: SpinWheelDataItem? = null,
    val openAlertDialog: Boolean = false
) : UiState {
    val isAddButtonEnabled: Boolean = textFieldContent.isNotEmpty()
    val isDeleteButtonEnabled: Boolean = spinWheelItemsList.isNotEmpty()
    val isSpinButtonEnabled: Boolean = !isSpinWheelSpinning && spinWheelItemsList.size > 1
}

sealed class SpinWheelEvents: UiEvent {
    data object SpinButtonClickedEvent : SpinWheelEvents()
    data object WheelStoppedEvent : SpinWheelEvents()
    data class TextFieldUpdatedEvent(val text: String) : SpinWheelEvents()
    data object AddButtonClickedEvent : SpinWheelEvents()
    data object DeleteButtonClickedEvent : SpinWheelEvents()
    data object AlertDismissedEvent : SpinWheelEvents()
}