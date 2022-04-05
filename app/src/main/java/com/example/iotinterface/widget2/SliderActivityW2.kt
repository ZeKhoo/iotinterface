package com.example.iotinterface.widget2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivitySliderW2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_slider_w2.*

class SliderActivityW2 : AppCompatActivity() {

    private lateinit var binding: ActivitySliderW2Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderW2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonSliderW2.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddSliderW2.setOnClickListener{
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

        val namesliderw2 = editTextTextSliderNameW2.text.toString().trim()
        val initialsliderw2 = editTextTextSliderInitialW2.text.toString().trim()
        val minsliderw2 = editTextTextSliderMinW2.text.toString().trim()
        val maxsliderw2 = editTextTextSliderMaxW2.text.toString().trim()
        val coloursliderw2 = editTextTextSliderColorW2.text.toString().trim()

        if(namesliderw2.isNotEmpty() && initialsliderw2.isNotEmpty() && minsliderw2.isNotEmpty() && maxsliderw2.isNotEmpty() && coloursliderw2.isNotEmpty()){
            val modelSliderW2 = DatabaseModelSliderW2(namesliderw2,initialsliderw2,minsliderw2,maxsliderw2,coloursliderw2)
            val id= reference.push().key

            reference.child(id!!).setValue(modelSliderW2)

            editTextTextSliderNameW2.setText("")
            editTextTextSliderInitialW2.setText("")
            editTextTextSliderMinW2.setText("")
            editTextTextSliderMaxW2.setText("")
            editTextTextSliderColorW2.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}