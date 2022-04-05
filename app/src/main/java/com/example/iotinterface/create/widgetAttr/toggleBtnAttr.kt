package com.example.iotinterface.create.widgetAttr

class toggleBtnAttr {
    var name: String = ""
    var status: String = ""
    val WIDGET_TYPE = "toggleButton"
    var color: String = ""

    constructor(name: String, status: String, color: String) {
        this.name = name
        this.status = status
        this.color = color
    }
}