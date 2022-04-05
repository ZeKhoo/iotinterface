package com.example.iotinterface.widget2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityOnOffW2Binding
import com.example.iotinterface.databinding.ActivityOnoffw1Binding
import com.example.iotinterface.widget1.DatabaseModelOnOffW1
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_on_off_w2.*
import kotlinx.android.synthetic.main.activity_onoffw1.*

class OnOffActivityW2 : AppCompatActivity() {

    private lateinit var binding: ActivityOnOffW2Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnOffW2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonOnOffW2.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddOnOffW2.setOnClickListener{
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
        val nameonw2 = editTextTextOnOffNameW2.text.toString().trim()
        val initialonw2 = editTextTextOnOffInitialW2.text.toString().trim()
        val colouronw2 = editTextTextOnOffColourW2.text.toString().trim()

        if(nameonw2 .isNotEmpty() && initialonw2.isNotEmpty() && colouronw2.isNotEmpty()){
            val modelOnW2 = DatabaseModelOnW2(nameonw2,initialonw2,colouronw2)
            val id= reference.push().key

            reference.child(id!!).setValue(modelOnW2)

            editTextTextOnOffNameW2.setText("")
            editTextTextOnOffInitialW2.setText("")
            editTextTextOnOffColourW2.setText("")

        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }

}