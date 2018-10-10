package com.example.juliobanda.udemy.ListView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.list_item.view.*

class ItemListAdapter(private val names: ArrayList<String>, private val mContext: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        view.list_item_text.text = names[position]

        return view
    }

    override fun getItem(position: Int): String = names[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = names.size

}