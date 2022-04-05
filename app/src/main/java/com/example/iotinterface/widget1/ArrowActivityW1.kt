package com.example.iotinterface.widget1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityArrowW1Binding
import com.example.iotinterface.databinding.ActivityNumpadw1Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_arrow_w1.*
import kotlinx.android.synthetic.main.activity_numpadw1.*

class ArrowActivityW1 : AppCompatActivity() {

    private lateinit var binding: ActivityArrowW1Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrowW1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonArrowW1.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddArrowW1.setOnClickListener{
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
        val namearroww1 = editTextTextArrowNameW1.text.toString().trim()
        val colourarroww1 = editTextTextArrowColorW1.text.toString().trim()
        val ok1arroww1 = spinnerArrowOk1W1.toString().trim()
        val ok2arroww1 = spinnerArrowOk2W1.toString().trim()

        if(namearroww1 .isNotEmpty()  && colourarroww1.isNotEmpty()){
            val modelArrowW1 = DatabaseModelArrowW1(namearroww1,colourarroww1,ok1arroww1,ok2arroww1)
            val id= reference.push().key

            reference.child(id!!).setValue(modelArrowW1)

            editTextTextArrowNameW1.setText("")
            editTextTextArrowColorW1.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}