package com.example.iotinterface.widget3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityColorPickW2Binding
import com.example.iotinterface.databinding.ActivityColorPickW3Binding
import com.example.iotinterface.widget2.DatabaseModelColorPickW2
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_color_pick_w2.*
import kotlinx.android.synthetic.main.activity_color_pick_w3.*

class Color_Pick_W3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityColorPickW3Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorPickW3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonColorPickW3.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddColorPickW3.setOnClickListener{
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
        val namecolourpw3 = editTextTextColorPickNameW3.text.toString().trim()

        if(namecolourpw3.isNotEmpty()){
            val modelColorPickW3 = DatabaseModelColorPickW3(namecolourpw3)
            val id= reference.push().key

            reference.child(id!!).setValue(modelColorPickW3)

            editTextTextColorPickNameW3.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }

}