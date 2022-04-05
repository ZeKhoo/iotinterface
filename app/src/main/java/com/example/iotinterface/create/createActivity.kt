package com.example.iotinterface.create

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.iotinterface.R
import com.example.iotinterface.create.widgetAttr.*
import com.example.iotinterface.databinding.ActivityCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


class createActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var widgetRecyclerView: RecyclerView
    private lateinit var wgtArrayList: ArrayList<Any>
//    private lateinit var buttonsList: ArrayList<View>
    private lateinit var radioBtnList: ArrayList<RadioButton>
    private var SHORT_DELAY: Int = 1000

    private var lcdfan: String = "1"
    private var lcdled: String = "red"
    private var lcdmode: String = "Fan"
    private var lcdTemperature: String = "20"

    //Firebase Reference
    private lateinit var dbRef: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
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

        //Set Text
        binding.textViewTtile.text = "Aircon"

        wgtArrayList = arrayListOf<Any>()
        val ledList = arrayOf("red", "blue", "green")
        val modelist = arrayOf("Fan", "Cool", "Sleep")

        wgtArrayList.add(toggleBtnAttr("switch ", "1", "green"))
        wgtArrayList.add(seekBarAttr("fan_speed", 0, 4, "1"))
        wgtArrayList.add(spinnerAttr("led", "red", ledList))
        wgtArrayList.add(radioBtnAttr("mode", 3, "Fan", "green", modelist))
        wgtArrayList.add(seekBarAttr("temperature", 16, 30, "20"))

//        var displayStatus:String = "Fan speed = "+"<b>"+lcdfan+"</b>"+"\nLed Color = "+"<b>"+lcdled+"</b>"+"\nMode = "+"<b>"+lcdmode+"</b>"+"Temperatuew = "+"<b>"+lcdTemperature+"</b>"
        var displayStatus:String = "Fan speed = "+lcdfan+"\nLed Color = "+lcdled+"\nMode = "+lcdmode+"\nTemperature = "+lcdTemperature
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
            val ll: LinearLayout = findViewById(R.id.container)
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

            Log.d("Create", lcdled)
            Log.d("Create", lcdmode)

            var updateStatus:String = "Fan speed = "+lcdfan+"\nLed Color = "+lcdled+"\nMode = "+lcdmode+"\nTemperature = "+lcdTemperature
            binding.textViewStatus.setText(updateStatus)

            Toast.makeText(this@createActivity,
                "Updated",
                Toast.LENGTH_SHORT
            ).show()

        }

        dbRef = Firebase.database.reference

    }

    private fun writeNewUser(arr: btnAttr, i: Int) {
        val format1 = SimpleDateFormat("yyyyMM")
        val child1 = format1.format(Date())

        val format2 = SimpleDateFormat("dd")
        val child2 = format2.format(Date())

        dbRef.child(arr.name).child("Status")

    }

    private fun addTextView(name: String, widgetType: String, status: String) : TextView {
        // creating TextView programmatically
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

        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                txtBox.text = btnAttr.name.toUpperCase()+"("+btnAttr.WIDGET_TYPE+"): 1"
            }
            else{
                txtBox.text = btnAttr.name.toUpperCase()+"("+btnAttr.WIDGET_TYPE+"): 0"
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
        val txtBox: TextView = addTextView(attr.name, attr.WIDGET_TYPE, attr.status)

        lcdled = attr.status

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
                    attr.name + ": " + attr.list[position],
                    Toast.LENGTH_SHORT
                ).show()

                txtBox.text = attr.name.toUpperCase()+"("+attr.WIDGET_TYPE+") : "+attr.list[position]
                lcdled = attr.list[position]
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

        lcdmode = attr.status

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
                txtBox.text = attr.name.toUpperCase()+"("+attr.WIDGET_TYPE+") : "+attr.list[i]
                lcdmode = attr.list[i].toString()
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
            lcdfan = value.toString()
        }else if (attr.name == "temperature")
            lcdTemperature = value.toString()

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
                    lcdfan = value.toString()
                }else if (attr.name == "temperature")
                    lcdTemperature = value.toString()
            }
        })
    }
}

