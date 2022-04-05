package com.example.iotinterface.widget3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityRadioW2Binding
import com.example.iotinterface.databinding.ActivityRadioW3Binding
import com.example.iotinterface.widget1.DatabaseModelRadioW1
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_radio_w2.*
import kotlinx.android.synthetic.main.activity_radio_w3.*

class RadioW3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRadioW3Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioW3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonRadioW3.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddRadioW3.setOnClickListener{
            sendData()
            finish()
        }

        val deviceName = "Aircon"
        val control_path = deviceName + "/Control/"


        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)

        binding.buttonConRadioW3.setOnClickListener {

            val optionW1 = binding.spinnerNumberRadioW3.selectedItemPosition

            if(optionW1 == 0){
                binding.editTextTextRadio2W3.setVisibility(View.GONE)
                binding.editTextTextRadio3W3.setVisibility(View.GONE)
            }

            else if(optionW1 == 1){
                binding.editTextTextRadio2W3.setVisibility(View.VISIBLE)
            }

            else if (optionW1 == 2){
                binding.editTextTextRadio3W3.setVisibility(View.VISIBLE)

            }

        }

    }

    private fun sendData() {
        val nameradiow3 = editTextTextRadioNameW3.text.toString().trim()
        val numberRadioW3 = spinnerNumberRadioW3.toString().trim()
        val radioButton1W3 = editTextTextRadio1W3.toString().trim()
        val radioButton2W3 = editTextTextRadio2W3.toString().trim()
        val radioButton3W3 = editTextTextRadio3W3.toString().trim()

        if( nameradiow3 .isNotEmpty()&&numberRadioW3.isNotEmpty() &&  radioButton1W3.isNotEmpty() && radioButton2W3.isNotEmpty() && radioButton3W3.isNotEmpty()){
            val modelRadioW3 = DatabaseModelRadioW3(nameradiow3,numberRadioW3,radioButton1W3,radioButton2W3,radioButton3W3)
            val id= reference.push().key

            reference.child(id!!).setValue(modelRadioW3)

            editTextTextRadioNameW3.setText("")
            editTextTextRadio1W3.setText("")
            editTextTextRadio2W3.setText("")
            editTextTextRadio3W3.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}