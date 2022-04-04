package com.example.iotinterface.create

import android.R.attr.description


class Attr {
    var name: String = ""
    var height: Int = 0
    var status: String = ""
    var width: Int = 0
    var widgetType: String = ""


    constructor(name_data:String, height_data: Int, width_data: Int, status_data: String, widgetType_data: String){
        this.name = name_data
        this.height = height_data
        this.height = width_data
        this.status = status_data
        this.widgetType = widgetType_data
    }

}