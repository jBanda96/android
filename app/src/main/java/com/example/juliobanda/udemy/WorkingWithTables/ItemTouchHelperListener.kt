package com.example.juliobanda.udemy.WorkingWithTables

import android.support.v7.widget.RecyclerView

interface ItemTouchHelperListener {
    fun onItemMoved(recyclerView: RecyclerView, fromPosition: Int, toPosition: Int): Boolean
    fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, position: Int)
}

interface ItemDragListener {
    fun onItemDrag(viewHolder: RecyclerView.ViewHolder)
}

interface ItemSelectedListener {
    fun onItemSelected()
    fun onItemClear()
}