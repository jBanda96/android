package com.example.juliobanda.udemy

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_item.view.*


class MainAdapter(private val items: ArrayList<Pair<String, Class<out AppCompatActivity>>>, private val onClickListener: OnClickMainView): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position], onClickListener)
    }

    class MainViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(item: Pair<String, Class<out AppCompatActivity>>, onClickListener: OnClickMainView){
            view.main_text_title.text = item.first

            view.setOnClickListener {
                onClickListener.onClick(item.first, item.second)
            }

        }

    }

}