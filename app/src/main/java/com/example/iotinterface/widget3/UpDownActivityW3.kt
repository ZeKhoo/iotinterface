package com.example.iotinterface.widget3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityUpDownW3Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_up_down_w3.*

class UpDownActivityW3 : AppCompatActivity() {

    private lateinit var binding: ActivityUpDownW3Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpDownW3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonAdjusterW3.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddAdjusterW3.setOnClickListener{
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

        val nameupw3 = editTextTextUpDownNameW3.text.toString().trim()
        val initialupw3 = editTextTextUpDownInitialW3.text.toString().trim()
        val minupw3 = editTextTextUpDownMinW3.text.toString().trim()
        val maxupw3 = editTextTextUpDownMaxW3.text.toString().trim()
        val colourupw3= editTextTextUpDownColourW3.text.toString().trim()

        if(nameupw3.isNotEmpty() && initialupw3.isNotEmpty() && minupw3.isNotEmpty() && maxupw3.isNotEmpty() && colourupw3.isNotEmpty()){
            val modelUpDownW3 = DatabaseModelUpW3(nameupw3,initialupw3,minupw3,maxupw3,colourupw3)
            val id= reference.push().key

            reference.child(id!!).setValue(modelUpDownW3)

            editTextTextUpDownNameW3.setText("")
            editTextTextUpDownInitialW3.setText("")
            editTextTextUpDownMinW3.setText("")
            editTextTextUpDownMaxW3.setText("")
            editTextTextUpDownColourW3.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}