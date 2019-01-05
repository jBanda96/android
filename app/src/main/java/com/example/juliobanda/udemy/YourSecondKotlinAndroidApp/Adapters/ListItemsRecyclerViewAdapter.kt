package com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.juliobanda.udemy.R
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Model.TaskList

class ListItemsRecyclerViewAdapter(var taskList: TaskList) : RecyclerView.Adapter<ListItemsRecyclerViewAdapter.ListItemViewHolder>() {

    override fun getItemCount(): Int = taskList.task.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_view_holder, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.taskTextView.text = taskList.task[position]
    }


    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTextView: TextView = itemView.findViewById(R.id.text_view_task)
    }

}