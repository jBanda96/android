package com.example.juliobanda.udemy.WorkingWithTables

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.activity_wtvdetail.*

class WTVDetailActivity : AppCompatActivity() {

    private val closeButton = View.OnClickListener {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wtvdetail)

        val detailText = intent.extras.getString(WorkingWithTablesActivity.wtvDetailText, "Error al mostrar el texto")
        wtv_detail_text.text = detailText
        wtv_detail_button.setOnClickListener(closeButton)
    }
}
