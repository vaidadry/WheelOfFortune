import androidx.lifecycle.ViewModel
import SpinWheelEvents.*
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ui.ColorListGenerator
import ui.SpinWheelDataItem

class SpinWheelViewModel: ViewModel() {

    private var state = MutableStateFlow(SpinWheelState())
    val uiState = state
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            state.value
        )

    fun onEvent(event: SpinWheelEvents) {
        when (event) {
            SpinButtonClickedEvent -> chooseRandomItemIndex()
            WheelStoppedEvent -> displayDialog()
            is TextFieldUpdatedEvent -> saveTextFieldValue(event.text)
            AddButtonClickedEvent -> updateSpinWheelItems()
            is DeleteButtonClickedEvent -> removeLastSelectedItem()
            AlertDismissedEvent -> hideDialog()
        }
    }

    private fun chooseRandomItemIndex() {
        val randomItem = state.value.spinWheelItemsList.random()
        val index = state.value.spinWheelItemsList.indexOf(randomItem)
        state.update {
            it.copy(
                selectedItemIndex = index,
                isSpinWheelSpinning = true,
                isNewIndexSelected = true,
                previouslySelectedItem = randomItem
            )
        }
    }

    private fun displayDialog() {
        val previouslySelectedItem = state.value.selectedItemIndex
        val text = state.value.spinWheelItemsList[previouslySelectedItem].text
        state.update {
            it.copy(
//                selectedItemIndex = 0,
                isNewIndexSelected = false,
                isSpinWheelSpinning = false,
                winner = text,
                openAlertDialog = true
            )
        }
    }

    private fun hideDialog() {
        state.update {
            it.copy(openAlertDialog = false)
        }
    }

    private fun saveTextFieldValue(text: String) {
        state.update {
            it.copy(textFieldContent = text)
        }
    }

    private fun updateSpinWheelItems() {
        val newItem = state.value.textFieldContent
        val nextIndex = state.value.spinWheelItemsList.size
        val list = state.value.spinWheelItemsList.toMutableList().apply {
            val newSlice = SpinWheelDataItem(
                colors = ColorListGenerator.getColorList(nextIndex),
                text = newItem
            )
            add(nextIndex, newSlice)
        }.toList()

        state.update {
            it.copy(
                spinWheelItemsList = list,
                textFieldContent = "",
                isNewIndexSelected = false,
                isSpinWheelSpinning = false
            )
        }
    }

    private fun removeLastSelectedItem() {
        val previouslySelectedItem = state.value.previouslySelectedItem
        val newList = if (previouslySelectedItem == null) {
            state.value.spinWheelItemsList.dropLast(1)
        } else {
            state.value.spinWheelItemsList.minus(previouslySelectedItem)
        }
        state.update {
            it.copy(
                spinWheelItemsList = newList,
                selectedItemIndex = 0,
                previouslySelectedItem = null,
                isNewIndexSelected = false,
                isSpinWheelSpinning = false
            )
        }
    }
}