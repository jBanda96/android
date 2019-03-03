package com.example.juliobanda.udemy.SharedPreferences

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.juliobanda.udemy.R

class PrefAdapter : RecyclerView.Adapter<PrefAdapter.ViewHolder>() {

    override fun getItemCount() = SharedPrefs.getTitles().size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pref_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pref: TextView = itemView.findViewById(R.id.prefText)

        fun bind() {
            this.pref.text = SharedPrefs.getTitles()[adapterPosition]
        }

    }

}