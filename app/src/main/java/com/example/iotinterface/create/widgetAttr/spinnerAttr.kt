package com.example.iotinterface.create.widgetAttr

class spinnerAttr {

    var name: String = ""
    var status: String = ""
    val WIDGET_TYPE = "Color Picker"
    var list: Array<String>

    constructor(name: String, status: String, list: Array<String>) {
        this.name = name
        this.status = status
        this.list = list
    }
}