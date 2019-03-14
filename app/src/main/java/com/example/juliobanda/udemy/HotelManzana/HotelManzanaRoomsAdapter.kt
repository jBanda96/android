package com.example.juliobanda.udemy.HotelManzana

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.activity_room_hotel_manzana.view.*

class HotelManzanaRoomsAdapter(private val rooms: ArrayList<String>, private val listener: HotelManzanaOnClickListener): RecyclerView.Adapter<HotelManzanaRoomsAdapter.HotelManzanaRoomViewHolder>() {

    override fun getItemCount(): Int = rooms.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelManzanaRoomViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_room_hotel_manzana, parent, false)
        return HotelManzanaRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelManzanaRoomViewHolder, position: Int) {
        holder.bind(rooms[position], listener)
    }

    class HotelManzanaRoomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(room: String, listener: HotelManzanaOnClickListener){
            view.hotel_manzana_room_text.text = room

            view.setOnClickListener {
                listener.onClickRoom(room)
            }

        }
    }
}

interface HotelManzanaOnClickListener {
    fun onClickRoom(room: String)
}