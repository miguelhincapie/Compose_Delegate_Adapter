package com.mahc.cda.lib

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

internal typealias Delegate = (ViewGroup) -> DelegateViewHolder

open class CompositeDelegateAdapter(delegateCapacity: Int) :
    RecyclerView.Adapter<DelegateViewHolder>() {

    private var delegateAdapters: SparseArrayCompat<Delegate> =
        SparseArrayCompat(delegateCapacity)
    private val items: MutableList<RecyclerViewType> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DelegateViewHolder {
        return delegateAdapters[viewType]?.invoke(parent)!!
    }

    override fun onBindViewHolder(holder: DelegateViewHolder, position: Int) {
        val viewType = items[position]
        holder.onBindViewHolder(viewType)
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun getItemCount() = items.size

    private fun dispatchUpdates(
        originalItems: MutableList<RecyclerViewType>,
        items: MutableList<RecyclerViewType>
    ) {
        DiffUtil.calculateDiff(DelegateDiffCallback(originalItems, items)).apply {
            dispatchUpdatesTo(this@CompositeDelegateAdapter)
        }
    }

    fun addNewItems(itemsToInsert: List<RecyclerViewType>) {
        val originalItems = items.toMutableList()
        if (itemsToInsert.isEmpty().not()) {
            items.addAll(itemsToInsert)
        }
        dispatchUpdates(originalItems, items)
    }

    fun addNewItem(itemToAdd: RecyclerViewType) = addNewItems(arrayListOf(itemToAdd))

    fun deleteItems(itemsToRemove: List<RecyclerViewType>) {
        val originalItems = items.toMutableList()
        if (itemsToRemove.isEmpty().not()) {
            items.removeAll(itemsToRemove)
        }
        dispatchUpdates(originalItems, items)
    }

    fun deleteItem(itemToRemove: RecyclerViewType) = deleteItems(arrayListOf(itemToRemove))

    fun updateItem(item: RecyclerViewType) = updateItems(arrayListOf(item))

    fun updateItems(itemsToUpdate: List<RecyclerViewType>) {
        val originalItems = items.toMutableList()
        items.clear()
        if (itemsToUpdate.isEmpty().not()) {
            items.addAll(itemsToUpdate)
        }
        dispatchUpdates(originalItems, items)
    }

    fun clearAll() = updateItems(emptyList())

    fun appendDelegate(viewType: Int, delegate: Delegate) {
        delegateAdapters.put(viewType, delegate)
    }
}