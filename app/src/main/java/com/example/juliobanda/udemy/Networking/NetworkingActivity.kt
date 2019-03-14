package com.example.juliobanda.udemy.Networking

import android.app.LoaderManager
import android.content.Loader
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.juliobanda.udemy.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_networking.*
import org.json.JSONArray

class NetworkingActivity : AppCompatActivity() {

    val onGoClick = View.OnClickListener {
        val user = networking_phone_number.text
        val pass = networking_password.text

        loaderManager.initLoader(2, null, object : LoaderManager.LoaderCallbacks<String> {
            override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {

                val jsonString = """
            {
                "authentication": {
                    "userID": "$user",
                    "consumerID": "10000033",
                    "authenticationType": "50",
                    "authenticationData": [
                        {
                            "idAuthenticationData": "password",
                            "authenticationData": [
                                "$pass"
                            ]
                        }
                    ]
                },
                "backendUserRequest": {
                    "userId": "",
                    "accessCode": "$user",
                    "dialogId": ""
                }
            }
            """

                return PostRequest(this@NetworkingActivity, jsonString)
            }

            override fun onLoadFinished(loader: Loader<String>?, data: String?) {
                Log.i(NetworkingActivity::class.java.canonicalName, data)
            }

            override fun onLoaderReset(loader: Loader<String>?) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)

        networking_progress.visibility = View.VISIBLE
        networking_progress.animate()

        networking_go_button.setOnClickListener(onGoClick)

        loaderManager.initLoader(1, null, object : LoaderManager.LoaderCallbacks<String> {
            override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
                return BackgroundLoader(this@NetworkingActivity)
            }

            override fun onLoadFinished(loader: Loader<String>?, data: String?) {
                val usersArray = parseJSON(data!!)
                val usersAdapter = UsersAdapter(usersArray)
                networking_recycler_view.apply {
                    adapter = usersAdapter
                    networking_progress.visibility = View.INVISIBLE
                }
            }

            override fun onLoaderReset(loader: Loader<String>?) {}
        })

    }

    fun parseGSON(json: String): ArrayList<User>{
        val users: ArrayList<User> = Gson().fromJson(json, arrayListOf<User>()::class.java)
        return users
    }

    fun parseJSON(json: String): ArrayList<User>{
        val users = arrayListOf<User>()

        val jsonArray = JSONArray(json)

        for (i in 0 until jsonArray.length()) {
            val id = jsonArray.getJSONObject(i).getInt("id")
            val name = jsonArray.getJSONObject(i).getString("name")
            val username = jsonArray.getJSONObject(i).getString("username")
            val email = jsonArray.getJSONObject(i).getString("email")
            val phone = jsonArray.getJSONObject(i).getString("phone")
            val website = jsonArray.getJSONObject(i).getString("website")

            val user = User(id, name, username, email, phone, website)
            users.add(user)
        }

        return users
    }

}