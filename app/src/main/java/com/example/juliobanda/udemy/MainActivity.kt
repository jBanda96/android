package com.example.juliobanda.udemy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import com.example.juliobanda.udemy.HotelManzana.HotelManzanaActivity
import com.example.juliobanda.udemy.Networking.NetworkingActivity
import com.example.juliobanda.udemy.WorkingWithTables.WorkingWithTablesActivity
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    private val onClickMainListener = object : OnClickMainView {
        override fun onClick(name: String, activity: Class<out AppCompatActivity>) {
            val intent = Intent(this@MainActivity, activity)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val titles = arrayListOf(
                Pair("Working with tables", WorkingWithTablesActivity::class.java),
                Pair("Hotel Manzana", HotelManzanaActivity::class.java),
                Pair("Networking", NetworkingActivity::class.java)
        )
        val mainAdapter = MainAdapter(titles, onClickMainListener)

        main_recycler_view.apply {
            adapter = mainAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

    }
}
