package com.example.iotinterface.create.IoTType

import com.google.firebase.database.Exclude

data class tvChild(
    var name: String = "Aircon",
    var change: String = "0",
    var brightness: String = "",
    var channel: String = "",
    var lcdchannel: String = "",
    var lcdshow: String = "",
    var ok: String = "0",
    var switch: String = "",
    var volume: String= ""
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "change" to change,
            "brightness" to brightness,
            "ok" to ok,
            "show" to "",
            "lcdshow" to "",
            "channel" to channel,
            "lcdchannel" to lcdchannel,
            "switch" to switch,
            "volume" to volume
        )
    }

}