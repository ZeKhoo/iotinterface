package com.example.iotinterface.widget4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityRadioW3Binding
import com.example.iotinterface.databinding.ActivityRadioW4Binding
import com.example.iotinterface.widget3.DatabaseModelRadioW3
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_radio_w3.*
import kotlinx.android.synthetic.main.activity_radio_w4.*

class RadioW4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRadioW4Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioW4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonRadioW4.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddRadioW4.setOnClickListener{
            sendData()
            finish()
        }

        val deviceName = "Aircon"
        val control_path = deviceName + "/Control/"


        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)

        binding.buttonConRadioW4.setOnClickListener {

            val optionW1 = binding.spinnerNumberRadioW4.selectedItemPosition

            if(optionW1 == 0){
                binding.editTextTextRadio2W4.setVisibility(View.GONE)
                binding.editTextTextRadio3W4.setVisibility(View.GONE)
            }

            else if(optionW1 == 1){
                binding.editTextTextRadio2W4.setVisibility(View.VISIBLE)
            }

            else if (optionW1 == 2){
                binding.editTextTextRadio3W4.setVisibility(View.VISIBLE)

            }

        }
    }

    private fun sendData() {

        val nameradiow4 = editTextTextRadioNameW4.text.toString().trim()
        val numberRadioW4 = spinnerNumberRadioW4.toString().trim()
        val radioButton1W4 = editTextTextRadio1W4.toString().trim()
        val radioButton2W4 = editTextTextRadio2W4.toString().trim()
        val radioButton3W4 = editTextTextRadio3W4.toString().trim()

        if( nameradiow4.isNotEmpty()&&numberRadioW4.isNotEmpty() &&  radioButton1W4.isNotEmpty() && radioButton2W4.isNotEmpty() && radioButton3W4.isNotEmpty()){
            val modelRadioW4 = DatabaseModelRadioW4(nameradiow4,numberRadioW4,radioButton1W4,radioButton2W4,radioButton3W4)
            val id= reference.push().key

            reference.child(id!!).setValue(modelRadioW4)

            editTextTextRadioNameW4.setText("")
            editTextTextRadio1W4.setText("")
            editTextTextRadio2W4.setText("")
            editTextTextRadio3W4.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}