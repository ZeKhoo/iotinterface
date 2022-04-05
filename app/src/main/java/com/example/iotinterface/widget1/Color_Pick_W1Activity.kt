package com.example.iotinterface.widget1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.airconActivity
import com.example.iotinterface.databinding.ActivityArrowW1Binding
import com.example.iotinterface.databinding.ActivityColorPickW1Binding
import com.example.iotinterface.databinding.ActivityOnoffw1Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_arrow_w1.*
import kotlinx.android.synthetic.main.activity_color_pick_w1.*

class Color_Pick_W1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityColorPickW1Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorPickW1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonColorPickW1.setOnClickListener {
            sendData()
            val intent = Intent(this, airconActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddColorPickW1.setOnClickListener{
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
        val namecolourpw1 = editTextTextColorPickNameW1.text.toString().trim()

        if(namecolourpw1.isNotEmpty()){
            val modelColorPickW1 = DatabaseModelColorPickW1(namecolourpw1)
            val id= reference.push().key

            reference.child(id!!).setValue(modelColorPickW1)

            editTextTextColorPickNameW1.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}