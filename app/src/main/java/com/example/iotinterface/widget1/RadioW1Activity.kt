package com.example.iotinterface.widget1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.iotinterface.R
import com.example.iotinterface.create.airconActivity
import com.example.iotinterface.databinding.ActivityArrowW1Binding
import com.example.iotinterface.databinding.ActivityRadioW1Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_arrow_w1.*
import kotlinx.android.synthetic.main.activity_radio_w1.*

class RadioW1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRadioW1Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRadioW1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonRadioW1.setOnClickListener {
            sendData()
            val intent = Intent(this, airconActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddRadioW1.setOnClickListener{
            sendData()
            finish()
        }

        val deviceName = "Aircon"
        val control_path = deviceName + "/Control/"


        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)

        binding.buttonConRadioW1.setOnClickListener {

            val optionW1 = binding.spinnerNumberRadioW1.selectedItemPosition

            if(optionW1 == 0){
                binding.editTextTextRadio2W1.setVisibility(View.GONE)
                binding.editTextTextRadio3W1.setVisibility(View.GONE)
            }

            else if(optionW1 == 1){
                binding.editTextTextRadio2W1.setVisibility(View.VISIBLE)
            }

            else if (optionW1 == 2){
                binding.editTextTextRadio3W1.setVisibility(View.VISIBLE)

            }

        }

    }

    private fun sendData() {
        val nameradiow1 = editTextTextRadioNameW1.text.toString().trim()
        val numberRadioW1 = spinnerNumberRadioW1.toString().trim()
        val radioButton1W1 = editTextTextRadio1W1.toString().trim()
        val radioButton2W1 = editTextTextRadio2W1.toString().trim()
        val radioButton3W1 = editTextTextRadio3W1.toString().trim()

        if( nameradiow1 .isNotEmpty()  &&numberRadioW1.isNotEmpty()){
            val modelRadioW1 = DatabaseModelRadioW1(nameradiow1,numberRadioW1,radioButton1W1,radioButton2W1,radioButton3W1)
            val id= reference.push().key

            reference.child(id!!).setValue(modelRadioW1)

            editTextTextRadioNameW1.setText("")
            editTextTextRadio1W1.setText("")
            editTextTextRadio2W1.setText("")
            editTextTextRadio3W1.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}