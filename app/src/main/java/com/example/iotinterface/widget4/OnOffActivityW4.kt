package com.example.iotinterface.widget4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityOnOffW4Binding
import com.example.iotinterface.widget3.DatabaseModelOnW3
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_on_off_w4.*

class OnOffActivityW4 : AppCompatActivity() {

    private lateinit var binding: ActivityOnOffW4Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnOffW4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonOnOffW4.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddOnOffW4.setOnClickListener{
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
        val nameonw4 = editTextTextOnOffNameW4.text.toString().trim()
        val initialonw4 = editTextTextOnOffInitialW4.text.toString().trim()
        val colouronw4 = editTextTextOnOffColourW4.text.toString().trim()

        if(nameonw4 .isNotEmpty() && initialonw4.isNotEmpty() && colouronw4.isNotEmpty()){
            val modelOnW4 = DatabaseModelOnW4(nameonw4,initialonw4,colouronw4)
            val id= reference.push().key

            reference.child(id!!).setValue(modelOnW4)

            editTextTextOnOffNameW4.setText("")
            editTextTextOnOffInitialW4.setText("")
            editTextTextOnOffColourW4.setText("")

        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}