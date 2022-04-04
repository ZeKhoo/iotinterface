package com.example.iotinterface

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.iotinterface.databinding.ActivityMainBinding
import com.example.iotinterface.widget1.*
import com.example.iotinterface.widget2.*
import com.example.iotinterface.widget3.*
import com.example.iotinterface.widget4.*
import com.example.iotinterface.widget5.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //testing github
    val widget1: Array<String> = arrayOf("On/Off", "Slider", "Number Pad","Arrow","Adjuster")
    val widget2: Array<String> = arrayOf("On/Off", "Slider", "Number Pad","Arrow","Adjuster")
    val widget3: Array<String> = arrayOf("On/Off", "Slider", "Number Pad","Arrow","Adjuster")
    val widget4: Array<String> = arrayOf("On/Off", "Slider", "Number Pad","Arrow","Adjuster")
    val widget5: Array<String> = arrayOf("On/Off", "Slider", "Number Pad","Arrow","Adjuster")

    override fun onCreate(savedInstanceState: Bundle?) {

        //testing github

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar

        actionBar!!.title = "IoT Widgets"

        super.onCreate(savedInstanceState)

        val arrayAdapter1 = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,widget1)
        val arrayAdapter2 = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,widget2)
        val arrayAdapter3 = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,widget3)
        val arrayAdapter4 = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,widget4)
        val arrayAdapter5 = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,widget5)

        binding.spinnerw1.adapter = arrayAdapter1
        binding.spinnerw2.adapter = arrayAdapter2
        binding.spinnerw3.adapter = arrayAdapter3
        binding.spinnerw4.adapter = arrayAdapter4
        binding.spinnerw5.adapter = arrayAdapter5

        binding.buttonNext.setOnClickListener {

            val option1 = binding.spinnerw1.selectedItemPosition

            if(option1 == 0){

                val intent = Intent(this, OnOffActivityW1::class.java)
                startActivity(intent)

            }
            else if(option1==1){
                val intent = Intent(this, SliderActivityW1::class.java)
                startActivity(intent)
            }

            else if(option1==2){
                val intent = Intent(this, NumPadActivityW1::class.java)
                startActivity(intent)
            }

            else if(option1==3){
                val intent = Intent(this, ArrowActivityW1::class.java)
                startActivity(intent)
            }

            else if(option1==4){
                val intent = Intent(this, UpDownActivityW1::class.java)
                startActivity(intent)
            }
        }

        binding.buttonNext2.setOnClickListener {
            val option2 = binding.spinnerw2.selectedItemPosition

            if(option2 == 0){
                val intent = Intent(this, OnOffActivityW2::class.java)
                startActivity(intent)
            }
            else if(option2==1){
                val intent = Intent(this, SliderActivityW2::class.java)
                startActivity(intent)
            }

            else if(option2==2){
                val intent = Intent(this,NumPadActivityW2::class.java)
                startActivity(intent)
            }

            else if(option2==3){
                val intent = Intent(this, ArrowActivityW2::class.java)
                startActivity(intent)
            }

            else if(option2==4){
                val intent = Intent(this, UpDownActivityW2::class.java)
                startActivity(intent)
            }
        }

        binding.buttonNext3.setOnClickListener {
            val option3 = binding.spinnerw3.selectedItemPosition

            if(option3 == 0){
                val intent = Intent(this, OnOffActivityW3::class.java)
                startActivity(intent)
            }
            else if(option3==1){
                val intent = Intent(this, SliderActivityW3::class.java)
                startActivity(intent)
            }

            else if(option3==2){
                val intent = Intent(this, NumPadActivityW3::class.java)
                startActivity(intent)
            }

            else if(option3==3){
                val intent = Intent(this, ArrowActivityW3::class.java)
                startActivity(intent)
            }

            else if(option3==4){
                val intent = Intent(this, UpDownActivityW3::class.java)
                startActivity(intent)
            }
        }

        binding.buttonNext4.setOnClickListener {
            val option4 = binding.spinnerw4.selectedItemPosition

            if(option4 == 0){
                val intent = Intent(this, OnOffActivityW4::class.java)
                startActivity(intent)
            }
            else if(option4==1){
                val intent = Intent(this, SliderActivityW4::class.java)
                startActivity(intent)
            }

            else if(option4==2){
                val intent = Intent(this, NumPadActivityW4::class.java)
                startActivity(intent)
            }

            else if(option4==3){
                val intent = Intent(this, ArrowActivityW4::class.java)
                startActivity(intent)
            }

            else if(option4==4){
                val intent = Intent(this, UpDownActivityW4::class.java)
                startActivity(intent)
            }
        }

        binding.buttonNext5.setOnClickListener {
            val option5 = binding.spinnerw5.selectedItemPosition

            if(option5 == 0){
                val intent = Intent(this, OnOffActivityW5::class.java)
                startActivity(intent)
            }
            else if(option5==1){
                val intent = Intent(this, SliderActivityW5::class.java)
                startActivity(intent)
            }

            else if(option5==2){
                val intent = Intent(this, NumPadActivityW5::class.java)
                startActivity(intent)
            }

            else if(option5==3){
                val intent = Intent(this, ArrowActivityW5::class.java)
                startActivity(intent)
            }

            else if(option5==4){
                val intent = Intent(this, UpDownActivityW5::class.java)
                startActivity(intent)
            }
        }


        binding.spinnerw1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               Toast.makeText(this@MainActivity, "You Select " + widget1[position], Toast.LENGTH_LONG).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        binding.spinnerw2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity, "You Select " + widget2[position], Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spinnerw3.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity, "You Select " + widget3[position], Toast.LENGTH_LONG).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.spinnerw4.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity, "You Select " + widget4[position], Toast.LENGTH_LONG).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.spinnerw5.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity, "You Select " + widget5[position], Toast.LENGTH_LONG).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


    }


}