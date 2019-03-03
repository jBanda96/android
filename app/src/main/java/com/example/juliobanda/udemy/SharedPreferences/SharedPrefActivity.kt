package com.example.juliobanda.udemy.SharedPreferences

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.juliobanda.udemy.R

class SharedPrefActivity : AppCompatActivity() {

    lateinit var prefRecyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref)

        prefRecyclerView = findViewById(R.id.prefRecyclerView)
        prefRecyclerView.adapter = PrefAdapter()
        prefRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}
