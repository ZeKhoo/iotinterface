package com.example.iotinterface.widget4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityNumPadW3Binding
import com.example.iotinterface.databinding.ActivityNumPadW4Binding
import com.example.iotinterface.widget3.DatabaseModelNumW3
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_num_pad_w3.*
import kotlinx.android.synthetic.main.activity_num_pad_w4.*

class NumPadActivityW4 : AppCompatActivity() {

    private lateinit var binding: ActivityNumPadW4Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumPadW4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonNumPadW4.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddNumW4.setOnClickListener{
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
        val namenumw4 = editTextTextNumPadNameW4.text.toString().trim()
        val colournumw4 = editTextTextNumPadColorW4.text.toString().trim()

        if(namenumw4 .isNotEmpty()  && colournumw4.isNotEmpty()){
            val modelNumW4 = DatabaseModelNumW4(namenumw4,colournumw4)
            val id= reference.push().key

            reference.child(id!!).setValue(modelNumW4)

            editTextTextNumPadNameW4.setText("")
            editTextTextNumPadColorW4.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}