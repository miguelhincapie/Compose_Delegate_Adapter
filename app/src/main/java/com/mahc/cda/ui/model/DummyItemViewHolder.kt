package com.mahc.cda.ui.model

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mahc.cda.R
import com.mahc.cda.databinding.RowBinding
import com.mahc.cda.lib.DelegateViewHolder
import com.mahc.cda.lib.RecyclerViewType

class DummyItemViewHolder(
    parent: ViewGroup
) : DelegateViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.row, parent, false)
) {
    private val binding = RowBinding.bind(itemView)

    override fun onBindViewHolder(recyclerViewType: RecyclerViewType) = with(binding) {
        (recyclerViewType as DummyUiRowModel).let {
            textId.text = it.id.toString()
            description.text = it.text
        }
    }

}