package com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.View
import android.widget.EditText
import com.example.juliobanda.udemy.ListView.MainActivity
import com.example.juliobanda.udemy.R
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Adapters.ListItemsRecyclerViewAdapter
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Model.TaskList
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.ToDoListActivity

class ListDetailActivity : AppCompatActivity() {

    private lateinit var list: TaskList
    private lateinit var listItemsRecyclerView: RecyclerView
    private lateinit var addTaskButton: FloatingActionButton

    private val addTaskButtonAction = View.OnClickListener { showCreateTaskDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        addTaskButton = findViewById(R.id.add_task_button)
        addTaskButton.setOnClickListener(addTaskButtonAction)

        list    =   intent.getParcelableExtra(ToDoListActivity.INTENT_LIST_KEY)
        title   =   list.name

        listItemsRecyclerView           =       findViewById(R.id.list_item_recycler_view)
        listItemsRecyclerView.adapter   =       ListItemsRecyclerViewAdapter(list)

    }

    private fun showCreateTaskDialog(){
        val editText        =   EditText(this)
        editText.inputType  =   InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this).setTitle(R.string.task_to_add).setView(editText).setPositiveButton(R.string.add_task, { dialog, _ ->
            val task = editText.text.toString()
            list.task.add(task)

            val recyclerAdapter = listItemsRecyclerView.adapter as ListItemsRecyclerViewAdapter
            recyclerAdapter.notifyItemInserted(list.task.size)

            dialog.dismiss()
        }).create().show()

    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(ToDoListActivity.INTENT_LIST_KEY, list)

        val intent = Intent()
        intent.putExtras(bundle)

        setResult(Activity.RESULT_OK, intent)

        super.onBackPressed()
    }

}
