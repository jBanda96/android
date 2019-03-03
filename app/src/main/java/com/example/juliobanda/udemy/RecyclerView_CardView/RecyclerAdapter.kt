package com.example.juliobanda.udemy.RecyclerView_CardView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class RecyclerAdapter(val array: ArrayList<String>, private val mContext: Context, private val onClickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun getItemCount(): Int = array.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = array[position]
        holder.bind(item, onClickListener)
    }


    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(item: String, onClickListener: OnItemClickListener){
            view.recycler_view_text.text = item

            view.setOnClickListener {
                onClickListener.onItemClick(item, adapterPosition)
            }
        }

    }

}