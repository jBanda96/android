package com.example.juliobanda.udemy.WorkingWithTables

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.activity_working_with_tables.*

class WorkingWithTablesActivity : AppCompatActivity(), LongClick, NoticeDialogListener {

    companion object {
        const val wtvDetailText = "WTV_DETAIL_TEXT"
    }

    private lateinit var textAdapter: WTVAdapter

    private var itemsArray = arrayListOf(
            "This is very simple text",
            "Long text goes here, Long text goes here, Long text goes here",
            "Some text goes here Some text goes here Some text goes here Some text goes here Some text goes here",
            "Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here Very looooooooooooong text goes here"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_working_with_tables)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Editing table"

        textAdapter = WTVAdapter(itemsArray, this)

        WTRecyclerView.apply {
            adapter = textAdapter
            addItemDecoration(DividerItemDecoration(this@WorkingWithTablesActivity, DividerItemDecoration.VERTICAL))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.wtv_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =

            when (item?.itemId) {

                R.id.wtvEdit -> {
                    Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.wtvMore -> {
                    this.showAddDialog()
                    true
                }

                android.R.id.home -> {
                    finish()
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }

    private fun showAddDialog(){
        val dialog = WTVNewDialog()
        dialog.show(supportFragmentManager, "dialog")
    }

    override fun longClick(text: String) {
        val detailIntent = Intent(this, WTVDetailActivity::class.java)
        detailIntent.putExtra(wtvDetailText, text)

        if(detailIntent.resolveActivity(packageManager) != null){
            startActivity(detailIntent)
        }

    }

    override fun onDialogPositiveClick(dialog: DialogFragment, newItem: String?) {
        if (newItem != null && newItem != "") {
            itemsArray.add(newItem)
            textAdapter.notifyItemInserted(itemsArray.size - 1)
        }
    }

    override fun onDialogNegativeClick() {}


}
