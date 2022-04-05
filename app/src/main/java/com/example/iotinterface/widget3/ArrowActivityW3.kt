package com.example.iotinterface.widget3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityArrowW2Binding
import com.example.iotinterface.databinding.ActivityArrowW3Binding
import com.example.iotinterface.widget2.DatabaseModelArrowW2
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_arrow_w2.*
import kotlinx.android.synthetic.main.activity_arrow_w3.*

class ArrowActivityW3 : AppCompatActivity() {

    private lateinit var binding: ActivityArrowW3Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArrowW3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonArrowW3.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddArrowW3.setOnClickListener{
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
        val namearroww3 = editTextTextArrowNameW3.text.toString().trim()
        val colourarroww3 = editTextTextArrowColorW3.text.toString().trim()
        val ok1arroww3 = spinnerArrowOk1W3.toString().trim()
        val ok2arroww3 = spinnerArrowOk2W3.toString().trim()

        if(namearroww3.isNotEmpty()  && colourarroww3.isNotEmpty()){
            val modelArrowW3 = DatabaseModelArrowW3(namearroww3,colourarroww3,ok1arroww3,ok2arroww3)
            val id= reference.push().key

            reference.child(id!!).setValue(modelArrowW3)

            editTextTextArrowNameW3.setText("")
            editTextTextArrowColorW3.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }

}