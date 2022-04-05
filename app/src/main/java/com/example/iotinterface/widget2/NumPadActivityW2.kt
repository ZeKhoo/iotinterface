package com.example.iotinterface.widget2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create.airconActivity
import com.example.iotinterface.databinding.ActivityNumPadW2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_num_pad_w2.*

class NumPadActivityW2 : AppCompatActivity() {

    private lateinit var binding: ActivityNumPadW2Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNumPadW2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")


        binding.buttonNumPadW2.setOnClickListener {
            sendData()
            val intent = Intent(this, airconActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddNumW2.setOnClickListener{
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
        val namenumw2 = editTextTextNumPadNameW2.text.toString().trim()
        val colournumw2 = editTextTextNumPadColorW2.text.toString().trim()

        if(namenumw2 .isNotEmpty()  && colournumw2.isNotEmpty()){
            val modelNumW2 = DatabaseModelNumW2(namenumw2,colournumw2)
            val id= reference.push().key

            reference.child(id!!).setValue(modelNumW2)

            editTextTextNumPadNameW2.setText("")
            editTextTextNumPadColorW2.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}