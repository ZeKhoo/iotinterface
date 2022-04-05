package com.example.iotinterface.create

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.iotinterface.R
import com.example.iotinterface.databinding.ActivityCreateBinding
import com.google.firebase.database.DatabaseReference
import java.text.SimpleDateFormat
import java.util.*


class createActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var widgetRecyclerView: RecyclerView
    private lateinit var wgtArrayList: ArrayList<Attr>
    private lateinit var buttonsList: ArrayList<Button>

    //Firebase Reference
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        //Declare arrayList Attr
//        widgetRecyclerView = binding.container
//        widgetRecyclerView.layoutManager = LinearLayoutManager(this)
//        widgetRecyclerView.setHasFixedSize(true)
//
//        widgetRecyclerView.addItemDecoration(
//            DividerItemDecoration(
//                binding.container.getContext(),
//                DividerItemDecoration.VERTICAL
//            )
//        )

        Log.d("Test1", "a")
        wgtArrayList = arrayListOf<Attr>()
        wgtArrayList.add(Attr("Aircon", 200, 300, "On", "button"))
        wgtArrayList.add(Attr("Fan", 200, 300, "Off", "toggleButton"))
        wgtArrayList.add(Attr("Aircon", 200, 300, "On", "button"))
        wgtArrayList.add(Attr("Fan", 200, 300, "On", "toggleButton"))


        Log.d("Test1", "b")
        for (i in 0 until wgtArrayList.size){
//            var name:String = wgtAttrList[i].name
//            var height:Int = wgtAttrList[i].height
//            var width:Int = wgtAttrList[i].width
//            var value: String = wgtAttrList[i].value

            Log.d("Test1", "c")
            when (wgtArrayList[i].widgetType){
                "button" -> addButton(wgtArrayList[i], i)
                "toggleButton" -> addToggleButton(wgtArrayList[i], i)
//                "radioButton" -> addRadioButton(wgtArrayList[i], i)

            }
        }

//        Log.d("Test1", "d")
//        val adapter = createAdapter(wgtArrayList)
//        widgetRecyclerView.adapter = adapter

//        adapter.setOnItemClickListener(onject:)

        //Write Firebase
//        for (i in 0 until wgtArrayList.size) {
//            writeNewUser(wgtArrayList[i], i)
//        }

//        val container: LinearLayout = findViewById(R.id.container)

//        val keypad = findViewById<View>(R.id.keypad) as TableLayout
//        keypad.visibility = View.GONE

//        fun onClick(arg0: View){
//            val layoutInflater = baseContext.getSystemService() as LayoutInflater
//        }


//        val llTestMenuMain = findViewById<LinearLayout>(R.id.llTestMenuMain)
//
//        //Create Recycle View dynamically in Linear Layout
//        val rv = RecyclerView(this)
//        val params = RecyclerView.LayoutParams(
//            RecyclerView.LayoutParams.MATCH_PARENT,
//            RecyclerView.LayoutParams.WRAP_CONTENT
//        )
//        rv.layoutParams = params
//
//        //Create Linear Layout in Recycler View
//        val llm = LinearLayoutManager(this)
//        adapter = RVAdapter_ButtonList(tests, null, this, buttonFontSize)
//        rv.adapter = adapter
//        rv.layoutManager = llm
//        rv.visibility = View.VISIBLE

        //Create Button based on arrayList
//        Create List of Button

    }



    private fun writeNewUser(arr: Attr, i: Int) {
        val format1 = SimpleDateFormat("yyyyMM")
        val child1 = format1.format(Date())

        val format2 = SimpleDateFormat("dd")
        val child2 = format2.format(Date())

        dbRef.child(arr.name).child("Status")

    }


    private fun addToggleButton(attr: Attr, position: Int) {
//        addTextView(attr.name, attr.widgetType)

        // Create ToggleButton programmatically.
        val toggleButton = ToggleButton(this)
        toggleButton.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        toggleButton.gravity = Gravity.CENTER
        toggleButton.isChecked = attr.status == "On"

        toggleButton.textOn = "ON"
        toggleButton.textOff = "OFF"

        // Add ToggleButton to LinearLayout

//        binding.rootLayout.addView(toggleButton)
//
//        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                Toast.makeText(this, "On", Toast.LENGTH_LONG).show()
//            }
//            else{
//                Toast.makeText(this, "Off", Toast.LENGTH_LONG).show()
//            }
//        }

    }

    private fun addTextView(name: String, widgetType: String) {
        // creating TextView programmatically
        val tv_dynamic = TextView(this)
        tv_dynamic.textSize = 20f
//        tv_dynamic.text = name + "(" +

        // add TextView to LinearLayout
//        binding.rootLayout.addView(tv_dynamic)
    }

    private fun addButton(arr: Attr, position: Int){
        Log.d("Test1", "e")
        // Create Button programmatically.
        val button = Button(this)

        Log.d("Test1", "i")
        //Set Width and Height of Button (width, height)
        button.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        Log.d("Test1", "j")
        (button.layoutParams as LinearLayout.LayoutParams).setMargins(10, 50, 10, 50)
        button.gravity = Gravity.CENTER
        button.text = arr.name
        button.isAllCaps = true

//        //Set Button ID (Int)
//        button.id = R.id.TEXT_ID

        Log.d("Test1", "k")
//        //add button in arraylist
//        buttonsList.add(button)
//
//        Log.d("Test1", "f")
//        // Add Button to LinearLayout
//        binding.rootLayout.addView(button)

        Log.d("Test1", "g")
        //SetOnClickListener
        button.setOnClickListener{
            Toast.makeText(this, "a"+position, Toast.LENGTH_LONG).show()
        }
        Log.d("Test1", "h")

//        val layout = findViewById<View>(R.id.rootLayout) as LinearLayout
//        layout.addView(button)

//        //Set Padding of Button
//        val padding = resources.getDimension(R.dimen.text_padding).toInt()
//        button.setPadding(padding, padding, padding, padding)
//
//        //Set Margin of Button
//        val margin = resources.getDimension(R.dimen.text_margin).toInt()
//        val layoutParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        layoutParams.setMargins(margin, margin, margin, margin)
//        button.layoutParams = layoutParams
//
//        //Set Background of Button
//        val color = ContextCompat.getColor(this, R.color.purple_200)
//        button.setBackgroundColor(color)
//
//        //Set Visibility
//        button.visibility = View.VISIBLE
//
//        //Set Text of Button
//        button.text = getString(R.string.click_on_me)
//
//        //Set Color Text of Button
//        val textColor = ContextCompat.getColor(this, R.color.black)
//        button.setTextColor(textColor)
//
//        //Set Gravity of Button == (Align)
//        button.gravity = Gravity.CENTER
//
//        //Set Text in Uppercase or Lowercase
//        button.text = "Hello Tutorialwing"
//        button.isAllCaps = true //HELLO TUTORIALWING
//        button.isAllCaps = false //Hello Tutorialwing
//
//        //Set Size of Text in Button
//        button.textSize = resources.getDimension(R.dimen.text_margin)
//
//        //Set Style (Bold/italic) of Text in Button
//        button.typeface = Typeface.DEFAULT_BOLD;
//
//        //Set Letter Spacing of Text in Button
//        button.letterSpacing = resources.getDimension(R.dimen.text_letter_spacing)

    }
}

