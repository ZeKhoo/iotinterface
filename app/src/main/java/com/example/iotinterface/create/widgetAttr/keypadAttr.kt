package com.example.iotinterface.create.widgetAttr

class keypadAttr {
    var name: String = ""
    var status: String = ""
    var row: Int = 4
    var col: Int = 3
    val WIDGET_TYPE = "Button"
    var color: String = ""
    var list: Array<String>

    constructor(
        name: String,
        status: String,
        row: Int,
        col: Int,
        color: String,
        list: Array<String>
    ) {
        this.name = name
        this.status = status
        this.row = row
        this.col = col
        this.color = color
        this.list = list
    }
}