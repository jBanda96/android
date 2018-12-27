package com.example.juliobanda.udemy.YourSecondKotlinAndroidApp

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.View
import android.widget.EditText
import com.example.juliobanda.udemy.R
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Adapters.ListSelectionRecyclerViewAdapter
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Managers.ListDataManager
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Model.TaskList

class ToDoListActivity : AppCompatActivity() {

    private lateinit var listsRecyclerView:    RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton

    val listDataManager: ListDataManager = ListDataManager(this)

    private val floatingButtonAction = { _: View ->
        showCreateListDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        val lists = listDataManager.readList()

        listsRecyclerView = findViewById(R.id.lists_recycler_view)
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists)

        floatingActionButton = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener(floatingButtonAction)

    }

    private fun showCreateListDialog(){
        val dialogTitle             =       getString(R.string.name_of_list)
        val positiveButtonTitle     =       getString(R.string.create_list)

        val builder             =       AlertDialog.Builder(this)

        val listTitleEditText       =       EditText(this)
        listTitleEditText.inputType =       InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            val list = TaskList(listTitleEditText.text.toString())
            listDataManager.saveList(list)

            val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            recyclerAdapter.addList(list)



            dialog.dismiss()
        }

        builder.create().show()

    }

}
