package com.example.juliobanda.udemy

import android.support.v7.app.AppCompatActivity

interface OnClickMainView {
    fun onClick(name: String, activity: Class<out AppCompatActivity>)
}