package com.example.iotinterface.create.IoTType

import com.google.firebase.database.Exclude

data class airconChild(
    var name: String = "Aircon",
    var fan_speed: String = "",
    var lcdFan: String = "",
    var lcdled: String = "",
    var lcdmode: String = "",
    var lcdTemp: String = "",
    var led: String = "",
    var mode: String = "",
    var switch: String= "",
    var temperature: String = "",
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "fan_speed" to fan_speed,
            "lcdFan" to lcdFan,
            "lcdled" to lcdled,
            "lcdmode" to lcdmode,
            "lcdTemp" to lcdTemp,
            "led" to led,
            "mode" to mode,
            "switch" to switch,
            "temperature" to temperature
        )
    }



}
