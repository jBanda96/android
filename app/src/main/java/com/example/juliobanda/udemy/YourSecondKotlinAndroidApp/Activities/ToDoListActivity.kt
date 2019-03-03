package com.example.juliobanda.udemy.YourSecondKotlinAndroidApp

import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.widget.EditText
import android.widget.ImageView
import com.example.juliobanda.udemy.R
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Activities.ListDetailActivity
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Adapters.ListSelectionRecyclerViewAdapter
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Adapters.ListSelectionRecyclerViewClickListener
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Managers.ListDataManager
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Model.TaskList

class ToDoListActivity : AppCompatActivity(), ListSelectionRecyclerViewClickListener {

    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 123
    }

    private lateinit var listsRecyclerView:     RecyclerView
    private lateinit var floatingActionButton:  FloatingActionButton
    private lateinit var animatedImage:         ImageView

    private val listDataManager: ListDataManager = ListDataManager(this)

    private val floatingButtonAction = { _: View ->
        showCreateListDialog()

        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        animation.duration = 700
        animatedImage.startAnimation(animation)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        val lists = listDataManager.readList()

        listsRecyclerView = findViewById(R.id.lists_recycler_view)
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)

        floatingActionButton = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener(floatingButtonAction)

        animatedImage = findViewById(R.id.animated_image_view)

    }

    override fun onResume() {
        super.onResume()

//        val animator = ObjectAnimator.ofFloat(floatingActionButton, "translationY", 200f, 0f)
//        animator.interpolator = BounceInterpolator()
//        animator.duration = 500
//        animator.start()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LIST_DETAIL_REQUEST_CODE) {
            data.let {
                listDataManager.saveList(data!!.getParcelableExtra(INTENT_LIST_KEY))
                updateList()
            }
        }

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
            showListDetail(list)
        }

        builder.create().show()

    }

    private fun showListDetail(list: TaskList) {
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)
        startActivityForResult(listDetailIntent, LIST_DETAIL_REQUEST_CODE)
    }

    override fun listItemClicked(list: TaskList) {
        showListDetail(list)
    }

    fun updateList() {
        val list = listDataManager.readList()
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(list, this)
    }

}
