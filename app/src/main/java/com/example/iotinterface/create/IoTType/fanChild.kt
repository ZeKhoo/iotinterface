package com.example.iotinterface.create.IoTType

import com.google.firebase.database.Exclude

data class fanChild(

    var name: String = "Fan",
    var brightness: String = "",
    var fan_speed: String = "",
    var led: String = "",
    var switch: String= "",
    ){
        @Exclude
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "name" to name,
                "brightness" to brightness,
                "fan_speed" to fan_speed,
                "led" to led,
                "switch" to switch
            )
        }
}
