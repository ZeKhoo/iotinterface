package com.example.iotinterface.widget2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityRadioW1Binding
import com.example.iotinterface.databinding.ActivityRadioW2Binding
import com.example.iotinterface.widget1.DatabaseModelRadioW1
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_radio_w1.*
import kotlinx.android.synthetic.main.activity_radio_w2.*

class RadioW2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRadioW2Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioW2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonRadioW2.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddRadioW2.setOnClickListener{
            sendData()
            finish()
        }

        val deviceName = "Aircon"
        val control_path = deviceName + "/Control/"


        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)

        binding.buttonConRadioW2.setOnClickListener {

            val optionW1 = binding.spinnerNumberRadioW2.selectedItemPosition

            if(optionW1 == 0){
                binding.editTextTextRadio2W2.setVisibility(View.GONE)
                binding.editTextTextRadio3W2.setVisibility(View.GONE)
            }

            else if(optionW1 == 1){
                binding.editTextTextRadio2W2.setVisibility(View.VISIBLE)
            }

            else if (optionW1 == 2){
                binding.editTextTextRadio3W2.setVisibility(View.VISIBLE)

            }

        }

    }

    private fun sendData() {
        val nameradiow2 = editTextTextRadioNameW2.text.toString().trim()
        val numberRadioW2 = spinnerNumberRadioW2.toString().trim()
        val radioButton1W2 = editTextTextRadio1W2.toString().trim()
        val radioButton2W2 = editTextTextRadio2W2.toString().trim()
        val radioButton3W2 = editTextTextRadio3W2.toString().trim()

        if( nameradiow2 .isNotEmpty()&&numberRadioW2.isNotEmpty() &&  radioButton1W2.isNotEmpty() && radioButton2W2.isNotEmpty() && radioButton3W2.isNotEmpty()){
            val modelRadioW2 = DatabaseModelRadioW2(nameradiow2,numberRadioW2,radioButton1W2,radioButton2W2,radioButton3W2)
            val id= reference.push().key

            reference.child(id!!).setValue(modelRadioW2)

            editTextTextRadioNameW2.setText("")
            editTextTextRadio1W2.setText("")
            editTextTextRadio2W2.setText("")
            editTextTextRadio3W2.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}