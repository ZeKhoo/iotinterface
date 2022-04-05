package com.example.iotinterface.widget5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.R
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityNumPadW4Binding
import com.example.iotinterface.databinding.ActivityNumPadW5Binding
import com.example.iotinterface.widget4.DatabaseModelNumW4
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_num_pad_w4.*
import kotlinx.android.synthetic.main.activity_num_pad_w5.*

class NumPadActivityW5 : AppCompatActivity() {

    private lateinit var binding: ActivityNumPadW5Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumPadW5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonNumPadW5.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddNumW5.setOnClickListener{
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
        val namenumw5 = editTextTextNumPadNameW5.text.toString().trim()
        val colournumw5 = editTextTextNumPadColorW5.text.toString().trim()

        if(namenumw5.isNotEmpty() && colournumw5.isNotEmpty()){
            val modelNumW5 = DatabaseModelNumW5(namenumw5,colournumw5)
            val id= reference.push().key

            reference.child(id!!).setValue(modelNumW5)

            editTextTextNumPadNameW5.setText("")
            editTextTextNumPadColorW5.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}