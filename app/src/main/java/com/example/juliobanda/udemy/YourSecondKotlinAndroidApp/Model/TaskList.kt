package com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Model

import android.os.Parcel
import android.os.Parcelable

class TaskList(val name: String, val task: ArrayList<String> = ArrayList()) : Parcelable {

    constructor(source: Parcel) : this (source.readString(), source.createStringArrayList())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(name)
        dest.writeStringList(task)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<TaskList> {
        override fun createFromParcel(parcel: Parcel): TaskList {
            return TaskList(parcel)
        }

        override fun newArray(size: Int): Array<TaskList?> {
            return arrayOfNulls(size)
        }
    }

}