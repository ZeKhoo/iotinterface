package com.example.iotinterface.widget3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityOnOffW3Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_on_off_w3.*

class OnOffActivityW3 : AppCompatActivity() {

    private lateinit var binding: ActivityOnOffW3Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnOffW3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonOnOffW3.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddOnOffW3.setOnClickListener{
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
        val nameonw3 = editTextTextOnOffNameW3.text.toString().trim()
        val initialonw3 = editTextTextOnOffInitialW3.text.toString().trim()
        val colouronw3 = editTextTextOnOffColourW3.text.toString().trim()

        if(nameonw3 .isNotEmpty() && initialonw3.isNotEmpty() && colouronw3.isNotEmpty()){
            val modelOnW3 = DatabaseModelOnW3(nameonw3,initialonw3,colouronw3)
            val id= reference.push().key

            reference.child(id!!).setValue(modelOnW3)

            editTextTextOnOffNameW3.setText("")
            editTextTextOnOffInitialW3.setText("")
            editTextTextOnOffColourW3.setText("")

        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }

}