package com.example.juliobanda.udemy.Networking

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.networking_item.view.*

class UsersAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.networking_item, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }


    class UsersViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(user: User){
            view.networking_name.text       =   user.name
            view.networking_username.text   =   user.username
            view.networking_email.text      =   user.email
        }
    }

}