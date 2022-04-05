package com.example.iotinterface.widget1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.airconActivity
import com.example.iotinterface.databinding.ActivityNumpadw1Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_numpadw1.*

class NumPadActivityW1 : AppCompatActivity() {

    private lateinit var binding: ActivityNumpadw1Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumpadw1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")


        binding.buttonNumPadW1.setOnClickListener {
            sendData()
            val intent = Intent(this, airconActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddNumW1.setOnClickListener{
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
        val namenumw1 = editTextTextNumPadNameW1.text.toString().trim()
        val colournumw1 = editTextTextNumPadColorW1.text.toString().trim()

        if(namenumw1 .isNotEmpty()  && colournumw1.isNotEmpty()){
            val modelNumW1 = DatabaseModelNumW1(namenumw1,colournumw1)
            val id= reference.push().key

            reference.child(id!!).setValue(modelNumW1)

            editTextTextNumPadNameW1.setText("")
            editTextTextNumPadColorW1.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }

}