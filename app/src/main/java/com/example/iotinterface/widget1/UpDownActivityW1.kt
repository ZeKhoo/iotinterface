package com.example.iotinterface.widget1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create. createActivity
import com.example.iotinterface.databinding.ActivityUpdownw1Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_updownw1.*

class UpDownActivityW1 : AppCompatActivity() {

    private lateinit var binding: ActivityUpdownw1Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdownw1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonAdjusterW1.setOnClickListener {
            sendData()
            val intent = Intent(this, com.example.iotinterface.create.createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddAdjusterW1.setOnClickListener{
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

        val nameupw1 = editTextTextUpDownNameW1.text.toString().trim()
        val initialupw1 = editTextTextUpDownInitialW1.text.toString().trim()
        val minupw1 = editTextTextUpDownMinW1.text.toString().trim()
        val maxupw1 = editTextTextUpDownMaxW1.text.toString().trim()
        val colourupw1 = editTextTextUpDownColourW1.text.toString().trim()

        if(nameupw1.isNotEmpty() && initialupw1.isNotEmpty() && minupw1.isNotEmpty() && maxupw1.isNotEmpty() && colourupw1.isNotEmpty()){
            val modelUpDownW1 = DatabaseModelUpDownW1(nameupw1,initialupw1,minupw1,maxupw1,colourupw1)
            val id= reference.push().key

            reference.child(id!!).setValue(modelUpDownW1)

            editTextTextUpDownNameW1.setText("")
            editTextTextUpDownInitialW1.setText("")
            editTextTextUpDownMinW1.setText("")
            editTextTextUpDownMaxW1.setText("")
            editTextTextUpDownColourW1.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}