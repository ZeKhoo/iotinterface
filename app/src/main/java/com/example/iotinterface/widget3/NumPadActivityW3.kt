package com.example.iotinterface.widget3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.createActivity
import com.example.iotinterface.databinding.ActivityNumPadW3Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_num_pad_w3.*

class NumPadActivityW3 : AppCompatActivity() {

    private lateinit var binding: ActivityNumPadW3Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumPadW3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")


        binding.buttonNumPadW3.setOnClickListener {
            sendData()
            val intent = Intent(this, createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddNumW3.setOnClickListener{
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
        val namenumw3 = editTextTextNumPadNameW3.text.toString().trim()
        val colournumw3 = editTextTextNumPadColorW3.text.toString().trim()

        if(namenumw3 .isNotEmpty()  && colournumw3.isNotEmpty()){
            val modelNumW3 = DatabaseModelNumW3(namenumw3,colournumw3)
            val id= reference.push().key

            reference.child(id!!).setValue(modelNumW3)

            editTextTextNumPadNameW3.setText("")
            editTextTextNumPadColorW3.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }

}