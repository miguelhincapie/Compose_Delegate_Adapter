package com.mahc.cda.ui.model

sealed class MainViewState {
    data object Loading : MainViewState()
    data class ShowingList(val dummyList: List<DummyUiRowModel>) : MainViewState()
}
