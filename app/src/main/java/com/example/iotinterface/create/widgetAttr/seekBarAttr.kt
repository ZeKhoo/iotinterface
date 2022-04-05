package com.example.iotinterface.create.widgetAttr

class seekBarAttr {
    var name: String = ""
    var min: Int = 0
    var max: Int = 0
    val WIDGET_TYPE = "Seek Bar"
    var status: String = ""

    constructor(name: String, min: Int, max: Int, status: String) {
        this.name = name
        this.min = min
        this.max = max
        this.status = status
    }
}