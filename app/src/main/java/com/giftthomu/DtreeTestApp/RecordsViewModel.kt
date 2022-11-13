package com.giftthomu.DtreeTestApp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecordsViewModel : ViewModel() {

    var state by mutableStateOf(RecordsScreenState())

    private var searchJob: Job? = null

    fun onAction(userAction: UserAction) {
        when (userAction) {
            UserAction.CloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
            }
            UserAction.SearchIconClicked -> {
                state = state.copy(isSearchBarVisible = true)
            }
            is UserAction.TextFieldInput -> {
                state = state.copy(searchText = userAction.text)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    searchRecordsInList(searchQuery = userAction.text)
                }
            }

        }
    }


    private fun searchRecordsInList(
        searchQuery: String
    ) {
        val newList = recordsList.filter {
            it.contains(searchQuery, ignoreCase = true)
        }
        state = state.copy(list = newList)
    }
}


sealed class UserAction {
    object SearchIconClicked : UserAction()
    object CloseIconClicked : UserAction()

    data class TextFieldInput(val text: String) : UserAction()

}


data class RecordsScreenState(
    val searchText: String = "",
    val list: List<String> = recordsList,
    val isSearchBarVisible: Boolean = false,
    val isSortMenuVisible: Boolean = false,
)