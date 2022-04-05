package com.example.iotinterface.widget3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivitySliderW3Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_slider_w3.*

class SliderActivityW3 : AppCompatActivity() {

    private lateinit var binding: ActivitySliderW3Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderW3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonSliderW3.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddSliderW3.setOnClickListener{
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

        val namesliderw3 = editTextTextSliderNameW3.text.toString().trim()
        val initialsliderw3 = editTextTextSliderInitialW3.text.toString().trim()
        val minsliderw3 = editTextTextSliderMinW3.text.toString().trim()
        val maxsliderw3 = editTextTextSliderMaxW3.text.toString().trim()
        val coloursliderw3 = editTextTextSliderColorW3.text.toString().trim()

        if(namesliderw3.isNotEmpty() && initialsliderw3.isNotEmpty() && minsliderw3.isNotEmpty() && maxsliderw3.isNotEmpty() && coloursliderw3.isNotEmpty()){
            val modelSliderW3 = DatabaseModelSliderW3(namesliderw3,initialsliderw3,minsliderw3,maxsliderw3,coloursliderw3)
            val id= reference.push().key

            reference.child(id!!).setValue(modelSliderW3)

            editTextTextSliderNameW3.setText("")
            editTextTextSliderInitialW3.setText("")
            editTextTextSliderMinW3.setText("")
            editTextTextSliderMaxW3.setText("")
            editTextTextSliderColorW3.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}