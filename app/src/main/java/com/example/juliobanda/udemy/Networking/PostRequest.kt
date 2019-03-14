package com.example.juliobanda.udemy.Networking

import android.content.AsyncTaskLoader
import android.content.Context
import java.io.BufferedOutputStream
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class PostRequest(context: Context, val payload: String) : AsyncTaskLoader<String>(context) {

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String {

        try {
            val url = URL("https://httpbin.org/post")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"

            val out = BufferedOutputStream(connection.outputStream)

            val writer = BufferedWriter(OutputStreamWriter(out, "UTF-8"))
            writer.write(payload)
            writer.flush()
            writer.close()

            out.close()

            return connection.inputStream.bufferedReader().use { it.readText() }
        } catch (error: Error) {
            return error.localizedMessage.toString()
        }

    }
}