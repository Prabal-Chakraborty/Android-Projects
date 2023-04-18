package com.example.scrollviewexample

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Avengers : AppCompatActivity() {
    var titleName : String? = "Avengers"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrollview_example)
        titleName = sharedPreferences.getString("Title","The Avengers")
        title = titleName
    }
}