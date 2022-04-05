package com.example.iotinterface.widget1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.databinding.ActivityOnoffw1Binding
import com.example.iotinterface.create.airconActivity
import com.example.iotinterface.create.fanActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_onoffw1.*

class OnOffActivityW1 : AppCompatActivity() {

    private lateinit var binding:ActivityOnoffw1Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityOnoffw1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")


        binding.buttonOnOffW1.setOnClickListener {
            sendData()
            val intent = Intent(this, fanActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddOnOffW1.setOnClickListener{
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
        val nameonw1 = editTextTextOnOffNameW1.text.toString().trim()
        val initialonw1 = editTextTextOnOffInitialW1.text.toString().trim()
        val colouronw1 = editTextTextOnOffColourW1.text.toString().trim()

        if(nameonw1 .isNotEmpty() && initialonw1.isNotEmpty() && colouronw1.isNotEmpty()){
            val modelOnOffW1 = DatabaseModelOnOffW1(nameonw1,initialonw1,colouronw1)
            val id= reference.push().key

            reference.child(id!!).setValue(modelOnOffW1)

            editTextTextOnOffNameW1.setText("")
            editTextTextOnOffInitialW1.setText("")
            editTextTextOnOffColourW1.setText("")

        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}