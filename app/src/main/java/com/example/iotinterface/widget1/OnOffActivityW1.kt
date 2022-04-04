package com.example.iotinterface.widget1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.DatabaseModel
import com.example.iotinterface.databinding.ActivityOnoffw1Binding
import com.example.iotinterface.create.createActivity
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
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddWidgetW1.setOnClickListener{
            sendData()
            finish()
        }

        val deviceName = "Aircon"
        val control_path = deviceName + "/Control/"


        val actionBar = supportActionBar
        actionBar!!.title = "Attributes Descriptor"
        actionBar.setDisplayHomeAsUpEnabled(true)

    }

    private fun sendData()
    {
        var name = editTextTextOnOffNameW1.text.toString().trim()
        var initial = editTextTextOnOffInitialW1.text.toString().trim()
        var colour = editTextTextOnOffColourW1.text.toString().trim()

        if(name.isNotEmpty() && initial.isNotEmpty() && colour.isNotEmpty()){
            var model = DatabaseModel(name,initial,colour)
            var id= reference.push().key

            reference.child(id!!).setValue(model)

            editTextTextOnOffNameW1.setText("")
            editTextTextOnOffInitialW1.setText("")
            editTextTextOnOffColourW1.setText("")

        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}