package com.example.iotinterface.widget1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

//import com.example.iotinterface.widget1.DatabaseModelSliderW1
import com.example.iotinterface.create.airconActivity
import com.example.iotinterface.databinding.ActivitySliderw1Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sliderw1.*

class SliderActivityW1 : AppCompatActivity() {

    private lateinit var binding: ActivitySliderw1Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderw1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonSliderW1.setOnClickListener {
            sendData()
            val intent = Intent(this, airconActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddSliderW1.setOnClickListener{
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

        val namesliderw1 = editTextTextSliderNameW1.text.toString().trim()
        val initialsliderw1 = editTextTextSliderInitialW1.text.toString().trim()
        val minsliderw1 = editTextTextSliderMinW1.text.toString().trim()
        val maxsliderw1 = editTextTextSliderMaxW1.text.toString().trim()
        val coloursliderw1 = editTextTextSliderColorW1.text.toString().trim()

        if(namesliderw1.isNotEmpty() && initialsliderw1.isNotEmpty() && minsliderw1.isNotEmpty() && maxsliderw1.isNotEmpty() && coloursliderw1.isNotEmpty()){
            val modelSliderW1 = DatabaseModelSliderW1(namesliderw1,initialsliderw1,minsliderw1,maxsliderw1,coloursliderw1)
            val id= reference.push().key

            reference.child(id!!).setValue(modelSliderW1)

            editTextTextSliderNameW1.setText("")
            editTextTextSliderInitialW1.setText("")
            editTextTextSliderMinW1.setText("")
            editTextTextSliderMaxW1.setText("")
            editTextTextSliderColorW1.setText("")
            }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}