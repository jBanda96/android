package com.example.juliobanda.udemy.WorkingWithTables

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.custom_dialog_wtv.view.*

class WTVNewDialog: DialogFragment() {

    lateinit var listener: NoticeDialogListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            listener = context as NoticeDialogListener
        } catch (error: Error){
            throw ClassCastException(activity!!.toString() + " must implement NoticeDialogListener")
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder   =   AlertDialog.Builder(activity)
        val inflater        =   activity?.layoutInflater

        val view = inflater?.inflate(R.layout.custom_dialog_wtv, null)
        dialogBuilder.setView(view)

        dialogBuilder.setPositiveButton(R.string.create) { _, _ ->
                    listener.onDialogPositiveClick(this, view?.wtv_new_text?.text.toString())
                }
                .setNegativeButton(R.string.cancel){ _, _ ->
                    listener.onDialogNegativeClick()
                }

        return dialogBuilder.create()
    }

}