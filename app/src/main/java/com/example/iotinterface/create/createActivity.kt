package com.example.iotinterface.create

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.iotinterface.R
import com.example.iotinterface.create.widgetAttr.*
import com.example.iotinterface.databinding.ActivityCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class createActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var widgetRecyclerView: RecyclerView
    private lateinit var wgtArrayList: ArrayList<Any>
    private lateinit var buttonsList: ArrayList<Button>
    private lateinit var radioBtnList: ArrayList<RadioButton>

    //Firebase Reference
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Declare arrayList Attr
//        widgetRecyclerView = binding.container
//        widgetRecyclerView.layoutManager = LinearLayoutManager(this)
//        widgetRecyclerView.setHasFixedSize(true)

//        widgetRecyclerView.addItemDecoration(
//            DividerItemDecoration(
//                binding.container.getContext(),
//                DividerItemDecoration.VERTICAL
//            )
//        )

//        wgtArrayList = arrayListOf<Any>()
//        val ledList = arrayOf("red", "blue", "green")
//        val modelist = arrayOf("Fan", "Cool", "Sleep")
//        wgtArrayList.add(seekBarAttr("fan_speed", 0, 100, "20"))
//        wgtArrayList.add(spinnerAttr("led", "0", ledList))
//        wgtArrayList.add(radioBtnAttr("mode", 0, "Fan", "red", modelist))
//        wgtArrayList.add(toggleBtnAttr("switch", "1", "red"))
//        wgtArrayList.add(seekBarAttr("temperature", 16, 30, "20"))
//
//        for (i in 0 until wgtArrayList.size){
//            var className = wgtArrayList.javaClass.simpleName
//            when(className){
//                "btnAttr" -> addButton(wgtArrayList[i] as btnAttr, i)
//                "toggleBtnAttr" -> addToggleButton(wgtArrayList[i] as toggleBtnAttr, i)
//                "spinnerAttr" -> addSpinner(wgtArrayList[i] as spinnerAttr, i)
//                "radioBtnAttr" -> addRadioButton(wgtArrayList[i] as radioBtnAttr, i)
//                "seekBarAttr" -> addSeekBar(wgtArrayList[i] as seekBarAttr, i)
//            }
//        }
//
//        dbRef = Firebase.database.reference


    }

    private fun writeNewUser(arr: btnAttr, i: Int) {
        val format1 = SimpleDateFormat("yyyyMM")
        val child1 = format1.format(Date())

        val format2 = SimpleDateFormat("dd")
        val child2 = format2.format(Date())

        dbRef.child(arr.name).child("Status")

    }

    private fun addTextView(name: String, widgetType: String) {
        // creating TextView programmatically
        val txt = TextView(this)
        txt.textSize = 20f
        txt.text = "$name($widgetType)"

        txt.gravity = Gravity.LEFT
        //add TextView to LinearLayout
        binding.container.addView(txt)
    }

    private fun addToggleButton(btnAttr: toggleBtnAttr, position: Int) {
        addTextView(btnAttr.name, btnAttr.WIDGET_TYPE)

        // Create ToggleButton programmatically.
        val toggleButton = ToggleButton(this)
        toggleButton.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        toggleButton.gravity = Gravity.CENTER
        toggleButton.isChecked = btnAttr.status == "On"

        toggleButton.textOn = "ON"
        toggleButton.textOff = "OFF"

        // Add ToggleButton to LinearLayout
        binding.container.addView(toggleButton)

        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "On", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Off", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun addButton(attr: btnAttr, position: Int){
        addTextView(attr.name, attr.WIDGET_TYPE)

        // Create Button programmatically.
        val button = Button(this)

        //Set Width and Height of Button (width, height)
        button.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        (button.layoutParams as LinearLayout.LayoutParams).setMargins(10, 50, 10, 50)
        button.gravity = Gravity.CENTER
        button.text = attr.name
        button.isAllCaps = true

//        //Set Button ID (Int)
//        button.id = R.id.TEXT_ID

        //add button in arraylist
//        buttonsList.add(button)

        // Add Button to LinearLayout
        binding.container.addView(button)

    }

    private fun addSpinner(attr: spinnerAttr, position: Int) {
        addTextView(attr.name, attr.WIDGET_TYPE)

        val spinner = Spinner(this)
        spinner.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, attr.list)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@createActivity,
                    getString(R.string.selected_item) + " " + attr.list[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

        // Add Spinner to LinearLayout
        binding.container.addView(spinner)
    }

    // Create RadioButton Dynamically
    private fun addRadioButton(attr: radioBtnAttr, position: Int) {

        addTextView(attr.name, attr.WIDGET_TYPE)

        for (i in 0 until attr.amount){
            val button = Button(this)

            //Set Width and Height of Button (width, height)
            button.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            (button.layoutParams as LinearLayout.LayoutParams).setMargins(10, 50, 10, 50)
            button.gravity = Gravity.CENTER
            button.text = attr.name
            button.isAllCaps = true

            // Add Spinner to LinearLayout
            binding.container.addView(button)

        }
    }

    private fun addSeekBar(seekBarAttr: seekBarAttr, i: Int) {

    }
}

