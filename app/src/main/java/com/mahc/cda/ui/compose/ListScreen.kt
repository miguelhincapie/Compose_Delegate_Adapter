package com.mahc.cda.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahc.cda.lib.CompositeDelegateAdapter
import com.mahc.cda.ui.model.DummyItemViewHolder
import com.mahc.cda.ui.model.DummyListDelegateViewType
import com.mahc.cda.ui.model.MainViewState
import com.mahc.cda.ui.viewmodel.MainViewModel

@Composable
fun ListScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val delegateAdapter = CompositeDelegateAdapter(1).apply {
        appendDelegate(
            DummyListDelegateViewType.MY_TYPE_ONE.ordinal
        ) { DummyItemViewHolder(it) }
    }
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            RecyclerView(context).apply {
                layoutManager = LinearLayoutManager(context)
                adapter = delegateAdapter
            }
        },
        update = { _ ->
            if (state is MainViewState.ShowingList) {
                delegateAdapter.addNewItems((state as MainViewState.ShowingList).dummyList)
            }
        }
    )
}

@Composable
@Preview(
    device = Devices.PIXEL_4,
    showBackground = true
)
private fun ListScreenPreview() {
    ListScreen()
}