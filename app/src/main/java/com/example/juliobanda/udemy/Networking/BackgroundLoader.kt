package com.example.juliobanda.udemy.Networking

import android.content.AsyncTaskLoader
import android.content.Context
import java.net.HttpURLConnection
import java.net.URL

class BackgroundLoader(context: Context) : AsyncTaskLoader<String>(context) {

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String {
        val connection = URL("https://jsonplaceholder.typicode.com/users").openConnection() as HttpURLConnection
        return connection.inputStream.bufferedReader().use { it.readText() }
    }
}