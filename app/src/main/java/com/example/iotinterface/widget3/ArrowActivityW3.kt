package com.example.iotinterface.widget3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iotinterface.R

class ArrowActivityW3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrow_w3)

        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}