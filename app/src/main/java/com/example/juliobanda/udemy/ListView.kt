package com.example.juliobanda.udemy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.juliobanda.udemy.Adapters.ItemListAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val names = arrayListOf("Julio", "Krystell", "Ericka", "Mario Banda Avila", "Mario Banda Castillo", "Jaime")
        val adapter = ItemListAdapter(names, this)

        listView.apply {
            setAdapter(adapter)
        }

    }
}
