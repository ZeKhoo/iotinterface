package com.example.iotinterface.create.IoTType

import com.google.firebase.database.Exclude

data class ceilingLightChild(
    var name: String = "Ceiling_Lights",
    var brightness: String = "",
    var color: String = "",
    var switch: String= "",
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "brightness" to brightness,
            "color" to color,
            "switch" to switch
        )
    }
}
