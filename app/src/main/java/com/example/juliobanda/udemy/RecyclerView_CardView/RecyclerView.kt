package com.example.juliobanda.udemy.RecyclerView_CardView

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerView : AppCompatActivity(), OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val adapter = RecyclerAdapter(arrayListOf("Julio", "Krystell"), this@RecyclerView, this@RecyclerView)
        recycler_view.adapter = adapter

    }

    override fun onItemClick(name: String, position: Int) {
        Toast.makeText(this, "$name in position $position", Toast.LENGTH_SHORT).show()
    }

}
