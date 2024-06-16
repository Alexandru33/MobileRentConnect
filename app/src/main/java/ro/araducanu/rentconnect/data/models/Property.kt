package ro.araducanu.rentconnect.data.models

import com.google.firebase.firestore.IgnoreExtraProperties

data class PropertyShort(
    val rating : Double,
    val reviews : Int,
    val title: String,
    val location : String,
    val rooms : Int,
    val size : Int,
    val price : String
    // TODO: Add image resource and unique identifier
)


@IgnoreExtraProperties
data class PropertyLong(
    var type : String ,
    var description : String,
    var yearOfConstruction : Int,
    var floor : String,
    var rating : Double,
    var reviews : Int,
    var title: String,
    var location : String,
    var rooms : Int,
    var size : Int,
    var price : String,
    var id : String? = null,
    var phone: String,
    var email : String


){
    constructor() : this (
        "",
        "",
        0,
        "",
        0.0,
        0,
        "",
        "",
        0,
        0,
        "",
        null,
        "",
        ""
    )

    fun toMap() : Map<String, Any?> {
        return mapOf(

            "type" to type ,
            "description" to description,
            "yearOfConstruction" to yearOfConstruction,
            "floor" to floor,
            "rating" to rating,
            "reviews" to reviews,
            "title" to title,
            "location" to location,
            "rooms" to rooms,
            "size" to size,
            "price" to price,
            "id"  to id,
            "phone" to phone,
            "email" to email

        )
    }
}



val propertyLongDummy : PropertyLong = PropertyLong(
    "Apartment",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod  tempor incididunt " +
            "ut labore et dolore magna aliqua. Ut enim ad minim  veniam, quis nostrud exercitation ullamco " +
            "laboris nisi ut aliquip ex ea  commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
            " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint  occaecat cupidatat non proident," +
            " sunt in culpa qui officia deserunt  mollit anim id est laborum.",
    1985,
    "3/4",
    4.5,
    5,
    "Apartament Lung Exemplu",
    "Bucharest, Romania",
    4,
    70,
    "400",
    "UNIQUEIDENTIFIERDUMMY123456",
    "0745498322",
    "radualex@gmail.com"
)
val propertyList = listOf<PropertyLong>(
    propertyLongDummy,
    propertyLongDummy
)

val propertyDummy : PropertyShort = PropertyShort( 4.2, 69, "Apartament 2 camere Politehnica", "Bucharest, Romania", 2, 46 , "350")