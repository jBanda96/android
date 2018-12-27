package com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Managers

import android.content.Context
import android.preference.PreferenceManager
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Model.TaskList

class ListDataManager(val context: Context) {

    fun saveList(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(list.name, list.task.toHashSet())
        sharedPreferences.apply()
    }

    fun readList(): ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferencesContents = sharedPreferences.all
        val taskList = ArrayList<TaskList>()

        for (preferenceItem in sharedPreferencesContents) {
            val itemHashSet = preferenceItem.value as HashSet<String>
            val list = TaskList(preferenceItem.key, ArrayList(itemHashSet))
            taskList.add(list)
        }

        return taskList
    }

}