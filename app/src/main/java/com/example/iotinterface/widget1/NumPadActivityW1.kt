package com.example.iotinterface.widget1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iotinterface.R

class NumPadActivityW1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numpadw1)

        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}