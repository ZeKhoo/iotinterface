package com.example.iotinterface.widget5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityRadioW4Binding
import com.example.iotinterface.databinding.ActivityRadioW5Binding
import com.example.iotinterface.widget4.DatabaseModelRadioW4
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_radio_w4.*
import kotlinx.android.synthetic.main.activity_radio_w5.*

class RadioW5Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRadioW5Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRadioW5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonRadioW5.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddRadioW5.setOnClickListener{
            sendData()
            finish()
        }

        val deviceName = "Aircon"
        val control_path = deviceName + "/Control/"


        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)

        binding.buttonConRadioW5.setOnClickListener {

            val optionW1 = binding.spinnerNumberRadioW5.selectedItemPosition

            if(optionW1 == 0){
                binding.editTextTextRadio2W5.setVisibility(View.GONE)
                binding.editTextTextRadio3W5.setVisibility(View.GONE)
            }

            else if(optionW1 == 1){
                binding.editTextTextRadio2W5.setVisibility(View.VISIBLE)
            }

            else if (optionW1 == 2){
                binding.editTextTextRadio3W5.setVisibility(View.VISIBLE)

            }

        }
    }

    private fun sendData() {

        val nameradiow5 = editTextTextRadioNameW5.text.toString().trim()
        val numberRadioW5 = spinnerNumberRadioW5.toString().trim()
        val radioButton1W5 = editTextTextRadio1W5.toString().trim()
        val radioButton2W5 = editTextTextRadio2W5.toString().trim()
        val radioButton3W5 = editTextTextRadio3W5.toString().trim()

        if( nameradiow5.isNotEmpty()&&numberRadioW5.isNotEmpty() &&  radioButton1W5.isNotEmpty() && radioButton2W5.isNotEmpty() && radioButton3W5.isNotEmpty()){
            val modelRadioW5 = DatabaseModelRadioW5(nameradiow5,numberRadioW5,radioButton1W5,radioButton2W5,radioButton3W5)
            val id= reference.push().key

            reference.child(id!!).setValue(modelRadioW5)

            editTextTextRadioNameW5.setText("")
            editTextTextRadio1W5.setText("")
            editTextTextRadio2W5.setText("")
            editTextTextRadio3W5.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}