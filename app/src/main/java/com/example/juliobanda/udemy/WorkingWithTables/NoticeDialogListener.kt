package com.example.juliobanda.udemy.WorkingWithTables

import android.support.v4.app.DialogFragment

interface NoticeDialogListener {
    fun onDialogPositiveClick(dialog: DialogFragment, newItem: String?)
    fun onDialogNegativeClick()
}