package com.mahc.cda.lib

interface RecyclerViewType {
    fun getDelegateId(): Int = this.hashCode()
    fun getViewType(): Int
}