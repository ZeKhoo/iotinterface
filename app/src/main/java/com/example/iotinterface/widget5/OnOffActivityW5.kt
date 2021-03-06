package com.example.iotinterface.widget5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityOnOffW4Binding
import com.example.iotinterface.databinding.ActivityOnOffW5Binding
import com.example.iotinterface.widget3.DatabaseModelOnW3
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_on_off_w4.*
import kotlinx.android.synthetic.main.activity_on_off_w5.*

class OnOffActivityW5 : AppCompatActivity() {

    private lateinit var binding: ActivityOnOffW5Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnOffW5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonOnOffW5.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddOnOffW5.setOnClickListener{
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
        val nameonw5 = editTextTextOnOffNameW5.text.toString().trim()
        val initialonw5 = editTextTextOnOffInitialW5.text.toString().trim()
        val colouronw5 = editTextTextOnOffColourW5.text.toString().trim()

        if(nameonw5.isNotEmpty() && initialonw5.isNotEmpty() && colouronw5.isNotEmpty()){
            val modelOnW5 = DatabaseModelOnW5(nameonw5,initialonw5,colouronw5)
            val id= reference.push().key

            reference.child(id!!).setValue(modelOnW5)

            editTextTextOnOffNameW5.setText("")
            editTextTextOnOffInitialW5.setText("")
            editTextTextOnOffColourW5.setText("")

        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}