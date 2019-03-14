package com.example.juliobanda.udemy.HotelManzana

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.activity_room_type.*

class HotelManzanaRoomTypeActivity : AppCompatActivity() {

    var selectedRoom: Int? = null
    val rooms = arrayListOf(
            "Two Queens",
            "One King",
            "Penthouse Suite"
    )

    lateinit var mAdapter: HotelManzanaRoomsAdapter

    private val onRoomClickListener = object : HotelManzanaOnClickListener {
        override fun onClickRoom(room: String) {
            val iResult = Intent()
            iResult.putExtra(HotelManzanaActivity.result, room)
            setResult(Activity.RESULT_OK, iResult)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_type)

        mAdapter = HotelManzanaRoomsAdapter(rooms, onRoomClickListener)

        hotel_manzana_recycler_room.apply {
            this.adapter = mAdapter
        }
    }
}
