package ro.araducanu.rentconnect.data.models

import com.google.firebase.Timestamp

data class Viewing(


    var date : Timestamp,
    var time : String,
    var emailLandlord : String,
    var emailTenant : String,
    var idProperty : String,
    var status : String




){

    constructor() : this (
        Timestamp.now(),
        "",
        "",
        "",
        "",
        "free"
    )

    fun toMap() : Map<String, Any?> {
        return mapOf(

            "time" to time,
            "date" to date,
            "emailLandlord" to emailLandlord,
            "emailTenant" to emailTenant,
            "idProperty" to idProperty,
            "status" to status
        )
    }
}