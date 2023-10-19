package com.mahc.cda.ui.model

import com.mahc.cda.lib.RecyclerViewType

data class DummyUiRowModel(val id: Int, val text: String) : RecyclerViewType {
    override fun getViewType(): Int = DummyListDelegateViewType.MY_TYPE_ONE.ordinal
}
