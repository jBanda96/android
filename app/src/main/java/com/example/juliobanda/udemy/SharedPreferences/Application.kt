package com.example.juliobanda.udemy.SharedPreferences

import android.content.Context

class Application: android.app.Application() {

    fun getAppContext(): Context = this.applicationContext
    
}