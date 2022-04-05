package com.example.iotinterface.widget4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityUpDownW3Binding
import com.example.iotinterface.databinding.ActivityUpDownW4Binding
import com.example.iotinterface.widget3.DatabaseModelUpW3
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_up_down_w3.*
import kotlinx.android.synthetic.main.activity_up_down_w4.*

class UpDownActivityW4 : AppCompatActivity() {

    private lateinit var binding: ActivityUpDownW4Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpDownW4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonAdjusterW4.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddAdjusterW4.setOnClickListener{
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

        val nameupw4 = editTextTextUpDownNameW4.text.toString().trim()
        val initialupw4 = editTextTextUpDownInitialW4.text.toString().trim()
        val minupw4 = editTextTextUpDownMinW4.text.toString().trim()
        val maxupw4 = editTextTextUpDownMaxW4.text.toString().trim()
        val colourupw4= editTextTextUpDownColourW4.text.toString().trim()

        if(nameupw4.isNotEmpty() && initialupw4.isNotEmpty() && minupw4.isNotEmpty() && maxupw4.isNotEmpty() && colourupw4.isNotEmpty()){
            val modelUpDownW4 = DatabaseModelUpW4(nameupw4,initialupw4,minupw4,maxupw4,colourupw4)
            val id= reference.push().key

            reference.child(id!!).setValue(modelUpDownW4)

            editTextTextUpDownNameW4.setText("")
            editTextTextUpDownInitialW4.setText("")
            editTextTextUpDownMinW4.setText("")
            editTextTextUpDownMaxW4.setText("")
            editTextTextUpDownColourW4.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}