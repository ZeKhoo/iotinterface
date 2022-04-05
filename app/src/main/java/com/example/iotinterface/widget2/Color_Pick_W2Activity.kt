package com.example.iotinterface.widget2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityColorPickW1Binding
import com.example.iotinterface.databinding.ActivityColorPickW2Binding
import com.example.iotinterface.widget1.DatabaseModelColorPickW1
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_color_pick_w1.*
import kotlinx.android.synthetic.main.activity_color_pick_w2.*

class Color_Pick_W2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityColorPickW2Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorPickW2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonColorPickW2.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddColorPickW2.setOnClickListener{
            sendData()
            finish()
        }

        val deviceName = "Aircon"
        val control_path = deviceName + "/Control/"


        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)

    }

    private fun sendData() {
        val namecolourpw2 = editTextTextColorPickNameW2.text.toString().trim()

        if(namecolourpw2.isNotEmpty()){
            val modelColorPickW2 = DatabaseModelColorPickW2(namecolourpw2)
            val id= reference.push().key

            reference.child(id!!).setValue(modelColorPickW2)

            editTextTextColorPickNameW2.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}