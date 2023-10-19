package com.mahc.cda.lib

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class DelegateViewHolder(view: View): RecyclerView.ViewHolder(view) {

    abstract fun onBindViewHolder(recyclerViewType: RecyclerViewType)
}