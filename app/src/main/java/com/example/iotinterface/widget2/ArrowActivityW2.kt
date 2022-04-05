package com.example.iotinterface.widget2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotinterface.create. createActivity
import com.example.iotinterface.databinding.ActivityArrowW2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_arrow_w2.*

class ArrowActivityW2 : AppCompatActivity() {

    private lateinit var binding: ActivityArrowW2Binding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrowW2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.buttonArrowW2.setOnClickListener {
            sendData()
            val intent = Intent(this,  createActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAddArrowW2.setOnClickListener{
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
        val namearroww2 = editTextTextArrowNameW2.text.toString().trim()
        val colourarroww2 = editTextTextArrowColorW2.text.toString().trim()
        val ok1arroww2 = spinnerArrowOk1W2.toString().trim()
        val ok2arroww2 = spinnerArrowOk2W2.toString().trim()

        if(namearroww2.isNotEmpty()  && colourarroww2.isNotEmpty()){
            val modelArrowW2 = DatabaseModelArrowW2(namearroww2,colourarroww2,ok1arroww2,ok2arroww2)
            val id= reference.push().key

            reference.child(id!!).setValue(modelArrowW2)

            editTextTextArrowNameW2.setText("")
            editTextTextArrowColorW2.setText("")
        }
        else{
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}