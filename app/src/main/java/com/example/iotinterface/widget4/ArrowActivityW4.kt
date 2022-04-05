package com.example.iotinterface.widget4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityArrowW3Binding
import com.example.iotinterface.databinding.ActivityArrowW4Binding
import com.example.iotinterface.widget3.DatabaseModelArrowW3
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_arrow_w3.*
import kotlinx.android.synthetic.main.activity_arrow_w4.*

class ArrowActivityW4 : AppCompatActivity() {

    private lateinit var binding: ActivityArrowW4Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrowW4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonArrowW4.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddArrowW4.setOnClickListener{
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
        val namearroww4 = editTextTextArrowNameW4.text.toString().trim()
        val colourarroww4 = editTextTextArrowColorW4.text.toString().trim()
        val ok1arroww4 = spinnerArrowOk1W4.toString().trim()
        val ok2arroww4 = spinnerArrowOk2W4.toString().trim()

        if(namearroww4.isNotEmpty()  && colourarroww4.isNotEmpty()){
            val modelArrowW4 = DatabaseModelArrowW4(namearroww4,colourarroww4,ok1arroww4,ok2arroww4)
            val id= reference.push().key

            reference.child(id!!).setValue(modelArrowW4)

            editTextTextArrowNameW4.setText("")
            editTextTextArrowColorW4.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}