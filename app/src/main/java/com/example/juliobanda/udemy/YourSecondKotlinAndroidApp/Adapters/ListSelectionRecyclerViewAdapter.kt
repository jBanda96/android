package com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.juliobanda.udemy.R
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Model.TaskList


class ListSelectionRecyclerViewAdapter(private val lists: ArrayList<TaskList>): RecyclerView.Adapter<ListSelectionRecyclerViewAdapter.ListSelectionViewHolder>() {

    override fun getItemCount(): Int = lists.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_selection_view_holder, parent, false)
        return ListSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listTitle.text = lists[position].name
        holder.listPosition.text = (position + 1).toString()
    }

    fun addList(lists: TaskList) {
        this.lists.add(lists)
        notifyDataSetChanged()
    }


    class ListSelectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var listPosition: TextView      =       itemView.findViewById(R.id.itemNumber)
        var listTitle:    TextView      =       itemView.findViewById(R.id.itemString)

    }
}