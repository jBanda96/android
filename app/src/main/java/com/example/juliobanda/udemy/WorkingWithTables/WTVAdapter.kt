package com.example.juliobanda.udemy.WorkingWithTables

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.main_item.view.*

class WTVAdapter(private val items: ArrayList<String>, private val longClickListener: LongClick): RecyclerView.Adapter<WTVAdapter.WTVViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WTVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent,false)
        return WTVViewHolder(view)
    }
    override fun onBindViewHolder(holder: WTVViewHolder, position: Int) {
        holder.bind(items[position], longClickListener)
    }


    class WTVViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(title: String, longClickListener: LongClick){
            view.main_text_title.text = title


            view.setOnClickListener {
                longClickListener.longClick(title)
            }
        }

    }

}