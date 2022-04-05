package com.example.iotinterface.create

import android.R
import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.iotinterface.create.IoTType.airconChild
import com.example.iotinterface.create.IoTType.fanChild
import com.example.iotinterface.create.widgetAttr.*
import com.example.iotinterface.databinding.ActivityCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class fanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var widgetRecyclerView: RecyclerView
    private lateinit var wgtArrayList: ArrayList<Any>
    //    private lateinit var buttonsList: ArrayList<View>
    private lateinit var radioBtnList: ArrayList<RadioButton>
    private var SHORT_DELAY: Int = 1000

    private var switch: String = "0"
    private var brightness: String = "50"
    private var fan_speed: String = "2"
    private var led: String = "red"

    //Firebase Reference
    private lateinit var dbRef: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set Text
        var widgetType: String = "Fan"
        binding.textViewTtile.text = widgetType

        wgtArrayList = arrayListOf<Any>()
        val ledList = arrayOf("red", "blue", "green")

        wgtArrayList.add(toggleBtnAttr("switch ", switch, "green"))
        wgtArrayList.add(seekBarAttr("brightness", 0, 100, brightness))
        wgtArrayList.add(seekBarAttr("fan_speed", 0, 4, fan_speed))
        wgtArrayList.add(spinnerAttr("led", led, ledList))

        var displayStatus:String = "Brightness = "+brightness+"\nFan Speed = "+fan_speed+"\nLed Color = "+led
        binding.textViewStatus.setText(displayStatus)

        for (i in 0 until wgtArrayList.size){
            var className = wgtArrayList[i].javaClass.kotlin.java.simpleName.toString()
            when(className){
                "btnAttr" -> addButton(wgtArrayList[i] as btnAttr, i)
                "toggleBtnAttr" -> addToggleButton(wgtArrayList[i] as toggleBtnAttr, i)
                "spinnerAttr" -> addSpinner(wgtArrayList[i] as spinnerAttr, i)
                "radioBtnAttr" -> addRadioButton(wgtArrayList[i] as radioBtnAttr, i)
                "seekBarAttr" -> addSeekBar(wgtArrayList[i] as seekBarAttr, i)
            }
        }

        binding.buttonReset.setOnClickListener(){
            val ll: LinearLayout = findViewById(com.example.iotinterface.R.id.container)
            ll.removeAllViews()

            for (i in 0 until wgtArrayList.size){
                var className = wgtArrayList[i].javaClass.kotlin.java.simpleName.toString()
                when(className){
                    "btnAttr" -> addButton(wgtArrayList[i] as btnAttr, i)
                    "toggleBtnAttr" -> addToggleButton(wgtArrayList[i] as toggleBtnAttr, i)
                    "spinnerAttr" -> addSpinner(wgtArrayList[i] as spinnerAttr, i)
                    "radioBtnAttr" -> addRadioButton(wgtArrayList[i] as radioBtnAttr, i)
                    "seekBarAttr" -> addSeekBar(wgtArrayList[i] as seekBarAttr, i)
                }
            }
        }

        binding.buttonConfirm.setOnClickListener(){

            var displayStatus:String = "Brightness = "+brightness+"\nFan Speed = "+fan_speed+"\nLed Color = "+led
            binding.textViewStatus.setText(displayStatus)

            Toast.makeText(this@fanActivity,
                "Updated",
                Toast.LENGTH_SHORT
            ).show()

            //Updated Firebase
            writeNewUser(widgetType, brightness, fan_speed, led, switch)
        }
    }

    private fun writeNewUser(
        name: String,
        brightness: String,
        fan_speed: String,
        led: String,
        switch: String
    ) {
        dbRef = Firebase.database.reference
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        val key = dbRef.child(name).child("Control").push().key
        if (key == null) {
            Log.w(ContentValues.TAG, "Couldn't get push key for posts")
            return
        }

        val post = fanChild(name, brightness, fan_speed, led, switch)
        val postValues = post.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/$name/Control" to postValues
        )

        dbRef.updateChildren(childUpdates).addOnSuccessListener {
            Toast.makeText(this@fanActivity, "Successfully added!!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this@fanActivity, "Failed!!", Toast.LENGTH_SHORT).show()
        }

    }


    private fun addTextView(name: String, widgetType: String, status: String) : TextView {
        // creating TextView programmatically
        Log.d("abcde",status)
        val txt = TextView(this)
        txt.textSize = 20f
        txt.gravity = Gravity.CENTER
        val nameUpper = name.toUpperCase()
        txt.text = "$nameUpper($widgetType): $status"

        txt.gravity = Gravity.LEFT
        //add TextView to LinearLayout
        binding.container.addView(txt)

        return txt
    }

    private fun addToggleButton(btnAttr: toggleBtnAttr, position: Int) {
        val txtBox: TextView = addTextView(btnAttr.name, btnAttr.WIDGET_TYPE, btnAttr.status)

        // Create ToggleButton programmatically.
        val toggleButton = ToggleButton(this)
        toggleButton.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        toggleButton.gravity = Gravity.CENTER
        toggleButton.isChecked = btnAttr.status.toString() == "1"

        toggleButton.textOn = "ON"
        toggleButton.textOff = "OFF"
//        if (btnAttr.color == "red")
//            toggleButton.setBackgroundColor(R.drawable.button_red)
//        else if (btnAttr.color == "blue")
//            toggleButton.setBackgroundColor(R.drawable.button_blue)
//        else if (btnAttr.color == "green")
//            toggleButton.setBackgroundColor(R.drawable.button_green)

        // Add ToggleButton to LinearLayout
        binding.container.addView(toggleButton)

        var sName: String = btnAttr.name.toString()
        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.d("Create", btnAttr.name+"yes")
                if (btnAttr.name.equals(sName)){
                    txtBox.text = btnAttr.name.toUpperCase()+"("+btnAttr.WIDGET_TYPE+"): 1"
                    switch = "1"
                }
            }
            else{
                Log.d("Create", btnAttr.name+"no")
                if (btnAttr.name.equals(sName)){
                    txtBox.text = btnAttr.name.toUpperCase()+"("+btnAttr.WIDGET_TYPE+"): 0"
                    switch = "0"
                }

            }
        }

    }

    private fun addButton(attr: btnAttr, position: Int){
        val txtBox: TextView = addTextView(attr.name, attr.WIDGET_TYPE, attr.status)

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
        Log.d("test1", attr.status+"2")
        val txtBox: TextView = addTextView(attr.name, attr.WIDGET_TYPE, attr.status)
        Log.d("test1", attr.status+"a")

        txtBox.text = attr.name.toUpperCase()+"("+attr.WIDGET_TYPE+") : "+attr.status
        led = attr.status
        Log.d("test1", attr.status+"b")

        val spinner = Spinner(this)
        spinner.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        Log.d("test1", attr.status+"c")

        var red: String = "red"
        var blue: String = "blue"
        var green: String = "green"
        Log.d("test1", attr.status+"d")

        if (attr.status.toString().equals(red)){
            spinner.setSelection(0, false)
        }else if (attr.status.toString().equals(blue)){
            spinner.setSelection(1, false)
        }else if (attr.status.toString().equals(green)){
            spinner.setSelection(2, false)
        }

        Log.d("test1", attr.status+"e")
        val arrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, attr.list)
        spinner.adapter = arrayAdapter
        Log.d("test1", attr.status+"f")

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (attr.name == "led"){
                    txtBox.text = attr.name.toUpperCase()+"("+attr.WIDGET_TYPE+") : "+attr.list[position]
                    led = attr.list[position]
                }

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
        val txtBox: TextView = addTextView(attr.name, attr.WIDGET_TYPE, attr.status)

        Log.d("Spinner", attr.name)
        Log.d("Spinner", attr.amount.toString())

        for (i in 0 until attr.amount){
            Log.d("Spinner", i.toString())
            val button = Button(this)

            Log.d("Spinner", "a")
            //Set Width and Height of Button (width, height)
            button.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            Log.d("Spinner", "b")
            button.gravity = Gravity.CENTER_HORIZONTAL
            Log.d("Spinner", "c")
            button.text = attr.list[i]
            button.isAllCaps = true
//            if (attr.color == "red")
//                button.setBackgroundColor(R.drawable.button_red)
//            else if (attr.color == "blue")
//                button.setBackgroundColor(R.drawable.button_blue)
//            else if (attr.color == "green")
//                button.setBackgroundColor(R.drawable.button_green)

            Log.d("Spinner", "success")

            button.setOnClickListener(){
                if (attr.name == "mode"){
                    txtBox.text = attr.name.toUpperCase()+"("+attr.WIDGET_TYPE+") : "+attr.list[i]
                }
            }

            // Add Spinner to LinearLayout
            binding.container.addView(button)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addSeekBar(attr: seekBarAttr, i: Int) {
        val txtBox: TextView = addTextView(attr.name, attr.WIDGET_TYPE, attr.status)

        var value: Int = attr.status.toInt()

        //pass current value
        if (attr.name == "fan_speed"){
            fan_speed = value.toString()
        }else if (attr.name == "brightness")
            brightness = value.toString()

        val seekBar = SeekBar(this)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(30, 30, 30, 30)
        var min: Int = attr.min
        var max: Int = attr.max
        seekBar.min = min
        seekBar.max = max
        seekBar.progress = value

        seekBar.layoutParams = layoutParams

        // Add SeekBar to LinearLayout
        binding.container.addView(seekBar)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                seekBar.setProgress(progress)
                txtBox.text = attr.name.toUpperCase()+"("+attr.WIDGET_TYPE+") : "+progress
                value = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (attr.name == "fan_speed"){
                    fan_speed = value.toString()
                }else if (attr.name == "brightness")
                    brightness = value.toString()
            }
        })
    }
}