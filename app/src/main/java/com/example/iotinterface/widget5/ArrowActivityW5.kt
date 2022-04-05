package com.example.iotinterface.widget5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityArrowW4Binding
import com.example.iotinterface.databinding.ActivityArrowW5Binding
import com.example.iotinterface.widget4.DatabaseModelArrowW4
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_arrow_w4.*
import kotlinx.android.synthetic.main.activity_arrow_w5.*

class ArrowActivityW5 : AppCompatActivity() {

    private lateinit var binding: ActivityArrowW5Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArrowW5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonArrowW5.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddArrowW5.setOnClickListener{
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
        val namearroww5 = editTextTextArrowNameW5.text.toString().trim()
        val colourarroww5 = editTextTextArrowColorW5.text.toString().trim()
        val ok1arroww5 = spinnerArrowOk1W5.toString().trim()
        val ok2arroww5 = spinnerArrowOk2W5.toString().trim()

        if(namearroww5.isNotEmpty()  && colourarroww5.isNotEmpty()){
            val modelArrowW5 = DatabaseModelArrowW5(namearroww5,colourarroww5,ok1arroww5,ok2arroww5)
            val id= reference.push().key

            reference.child(id!!).setValue(modelArrowW5)

            editTextTextArrowNameW5.setText("")
            editTextTextArrowColorW5.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}