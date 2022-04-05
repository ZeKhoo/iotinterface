package com.example.iotinterface.widget5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityUpDownW4Binding
import com.example.iotinterface.databinding.ActivityUpDownW5Binding
import com.example.iotinterface.widget4.DatabaseModelUpW4
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_up_down_w4.*
import kotlinx.android.synthetic.main.activity_up_down_w5.*

class UpDownActivityW5 : AppCompatActivity() {

    private lateinit var binding: ActivityUpDownW5Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpDownW5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonAdjusterW5.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddAdjusterW5.setOnClickListener{
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

        val nameupw5 = editTextTextUpDownNameW5.text.toString().trim()
        val initialupw5 = editTextTextUpDownInitialW5.text.toString().trim()
        val minupw5 = editTextTextUpDownMinW5.text.toString().trim()
        val maxupw5 = editTextTextUpDownMaxW5.text.toString().trim()
        val colourupw5= editTextTextUpDownColourW5.text.toString().trim()

        if(nameupw5.isNotEmpty() && initialupw5.isNotEmpty() && minupw5.isNotEmpty() && maxupw5.isNotEmpty() && colourupw5.isNotEmpty()){
            val modelUpDownW5 = DatabaseModelUpW5(nameupw5,initialupw5,minupw5,maxupw5,colourupw5)
            val id= reference.push().key

            reference.child(id!!).setValue(modelUpDownW5)

            editTextTextUpDownNameW5.setText("")
            editTextTextUpDownInitialW5.setText("")
            editTextTextUpDownMinW5.setText("")
            editTextTextUpDownMaxW5.setText("")
            editTextTextUpDownColourW5.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}