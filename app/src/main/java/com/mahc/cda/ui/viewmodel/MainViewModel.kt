package com.mahc.cda.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahc.cda.ui.model.DummyUiRowModel
import com.mahc.cda.ui.model.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState.Loading)
    val state = _state.asStateFlow()

    init {
        loadDummyValues()
    }

    fun loadDummyValues() = viewModelScope.launch {
        _state.value = MainViewState.ShowingList(generateDummyData())
    }

    private fun generateDummyData(): List<DummyUiRowModel> {
        return mutableListOf<DummyUiRowModel>().apply {
            for (i in 0..10) {
                add(DummyUiRowModel(i + 1, "dummy text for row: ${i + 1}"))
            }
        }
    }
}