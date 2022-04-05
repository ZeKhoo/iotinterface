package com.example.iotinterface.widget5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivitySliderW4Binding
import com.example.iotinterface.databinding.ActivitySliderW5Binding
import com.example.iotinterface.widget4.DatabaseModelSliderW4
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_slider_w4.*
import kotlinx.android.synthetic.main.activity_slider_w5.*

class SliderActivityW5 : AppCompatActivity() {

    private lateinit var binding: ActivitySliderW5Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderW5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonSliderW5.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddSliderW5.setOnClickListener{
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

        val namesliderw5 = editTextTextSliderNameW5.text.toString().trim()
        val initialsliderw5 = editTextTextSliderInitialW5.text.toString().trim()
        val minsliderw5 = editTextTextSliderMinW5.text.toString().trim()
        val maxsliderw5 = editTextTextSliderMaxW5.text.toString().trim()
        val coloursliderw5 = editTextTextSliderColorW5.text.toString().trim()

        if(namesliderw5.isNotEmpty() && initialsliderw5.isNotEmpty() && minsliderw5.isNotEmpty() && maxsliderw5.isNotEmpty() && coloursliderw5.isNotEmpty()){
            val modelSliderW5 = DatabaseModelSliderW5(namesliderw5,initialsliderw5,minsliderw5,maxsliderw5,coloursliderw5)
            val id= reference.push().key

            reference.child(id!!).setValue(modelSliderW5)

            editTextTextSliderNameW5.setText("")
            editTextTextSliderInitialW5.setText("")
            editTextTextSliderMinW5.setText("")
            editTextTextSliderMaxW5.setText("")
            editTextTextSliderColorW5.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}