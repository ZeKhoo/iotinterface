package com.example.iotinterface.widget4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivitySliderW4Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_slider_w4.*

class SliderActivityW4 : AppCompatActivity() {

    private lateinit var binding: ActivitySliderW4Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderW4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonSliderW4.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddSliderW4.setOnClickListener{
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

        val namesliderw4 = editTextTextSliderNameW4.text.toString().trim()
        val initialsliderw4 = editTextTextSliderInitialW4.text.toString().trim()
        val minsliderw4 = editTextTextSliderMinW4.text.toString().trim()
        val maxsliderw4 = editTextTextSliderMaxW4.text.toString().trim()
        val coloursliderw4 = editTextTextSliderColorW4.text.toString().trim()

        if(namesliderw4.isNotEmpty() && initialsliderw4.isNotEmpty() && minsliderw4.isNotEmpty() && maxsliderw4.isNotEmpty() && coloursliderw4.isNotEmpty()){
            val modelSliderW4 = DatabaseModelSliderW4(namesliderw4,initialsliderw4,minsliderw4,maxsliderw4,coloursliderw4)
            val id= reference.push().key

            reference.child(id!!).setValue(modelSliderW4)

            editTextTextSliderNameW4.setText("")
            editTextTextSliderInitialW4.setText("")
            editTextTextSliderMinW4.setText("")
            editTextTextSliderMaxW4.setText("")
            editTextTextSliderColorW4.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }

}