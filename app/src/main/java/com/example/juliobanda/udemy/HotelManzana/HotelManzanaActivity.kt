package com.example.juliobanda.udemy.HotelManzana

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.activity_hotel_manzana.*

class HotelManzanaActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    companion object {
        const val result = "RESULT"
    }

    private enum class Check {
        In, Out
    }

    private var check = Check.In

    private val checkListener = View.OnClickListener {

        check = if (it.tag == "100") {
            Check.In
        } else {
            Check.Out
        }

        val datePicker = HotelManzanaDatePicker()
        datePicker.show(supportFragmentManager, "DatePicker")
    }

    private val roomListener = View.OnClickListener {
        val intent = Intent(this, HotelManzanaRoomTypeActivity::class.java)
        startActivityForResult(intent, 150)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_manzana)

        hm_check_in.setOnClickListener(checkListener)
        hm_check_out.setOnClickListener(checkListener)

        hm_room_type.setOnClickListener(roomListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 150 && resultCode == Activity.RESULT_OK) {
            hm_room_type.text = data?.getStringExtra(result)
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        when (check) {
            HotelManzanaActivity.Check.In -> hm_check_in_text.text = resources.getString(R.string.complete_date, convertIntToMonth(month), dayOfMonth, year)
            HotelManzanaActivity.Check.Out -> hm_check_out_text.text = resources.getString(R.string.complete_date, convertIntToMonth(month), dayOfMonth, year)
        }

    }

    private fun convertIntToMonth(month: Int): String =

            when (month) {
                0 -> getString(R.string.january)
                1 -> getString(R.string.february)
                2 -> getString(R.string.march)
                3 -> getString(R.string.april)
                4 -> getString(R.string.may)
                5 -> getString(R.string.june)
                6 -> getString(R.string.july)
                7 -> getString(R.string.august)
                8 -> getString(R.string.september)
                9 -> getString(R.string.october)
                11 -> getString(R.string.november)
                10 -> getString(R.string.december)
                else -> ""
            }

}
