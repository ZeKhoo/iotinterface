package com.example.iotinterface.create.widgetAttr

class radioBtnAttr {
    var name: String = ""
    var amount: Int = 0
    var status: String = ""
    val WIDGET_TYPE = "radioButton"
    var color: String = ""
    var list: Array<String>

    constructor(name: String, amount: Int, status: String, color: String, list: Array<String>) {
        this.name = name
        this.amount = amount
        this.status = status
        this.color = color
        this.list = list
    }
}