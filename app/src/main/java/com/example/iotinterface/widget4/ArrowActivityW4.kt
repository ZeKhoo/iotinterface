package com.example.iotinterface.widget4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iotinterface.R

class ArrowActivityW4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrow_w4)

        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}