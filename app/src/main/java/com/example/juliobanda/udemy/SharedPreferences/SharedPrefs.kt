package com.example.juliobanda.udemy.SharedPreferences

import android.preference.PreferenceManager

object SharedPrefs {
    private fun sharedPrefs() = PreferenceManager.getDefaultSharedPreferences(Application().getAppContext())

    fun saveTitle(title: String){
        val editor = sharedPrefs().edit()
        editor.putString("TITLE", title).apply()
    }

    fun getTitles(): List<String> {
        return sharedPrefs().all.values.map { it.toString() }
    }
}