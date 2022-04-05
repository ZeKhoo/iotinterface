package com.example.iotinterface.widget2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityUpDownW2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_up_down_w2.*

class UpDownActivityW2 : AppCompatActivity() {

    private lateinit var binding: ActivityUpDownW2Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpDownW2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonAdjusterW2.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddAdjusterW2.setOnClickListener{
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

        val nameupw2 = editTextTextUpDownNameW2.text.toString().trim()
        val initialupw2 = editTextTextUpDownInitialW2.text.toString().trim()
        val minupw2 = editTextTextUpDownMinW2.text.toString().trim()
        val maxupw2 = editTextTextUpDownMaxW2.text.toString().trim()
        val colourupw2= editTextTextUpDownColourW2.text.toString().trim()

        if(nameupw2.isNotEmpty() && initialupw2.isNotEmpty() && minupw2.isNotEmpty() && maxupw2.isNotEmpty() && colourupw2.isNotEmpty()){
            val modelUpDownW2 = DatabaseModelUpW2(nameupw2,initialupw2,minupw2,maxupw2,colourupw2)
            val id= reference.push().key

            reference.child(id!!).setValue(modelUpDownW2)

            editTextTextUpDownNameW2.setText("")
            editTextTextUpDownInitialW2.setText("")
            editTextTextUpDownMinW2.setText("")
            editTextTextUpDownMaxW2.setText("")
            editTextTextUpDownColourW2.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}