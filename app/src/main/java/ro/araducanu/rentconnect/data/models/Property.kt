package ro.araducanu.rentconnect.data.models

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

data class PropertyLong(
    val type : String ,
    val description : String,
    val yearOfConstruction : Int,
    val floor : String,
    val rating : Double,
    val reviews : Int,
    val title: String,
    val location : String,
    val rooms : Int,
    val size : Int,
    val price : String,
    val id : String,
    val phone: String


    // TODO: Add image list resource and unique identifier
)

val propertyList = listOf<PropertyShort>(
    PropertyShort( 4.5, 59, "Apartament 3 camere Lujerului", "Bucharest, Romania", 3, 76 , "450"),
    PropertyShort( 4.2, 69, "Apartament 2 camere Politehnica", "Bucharest, Romania", 2, 46 , "350"),
    PropertyShort( 4.5, 78, "Apartament 4 camere Giulesti", "Bucharest, Romania", 4, 71 , "560"),
    PropertyShort( 4.5, 78, "Apartament 4 camere Piata Sudului", "Bucharest, Romania", 4, 71 , "560"),
    PropertyShort( 4.5, 78, "Apartament 4 camere Crangasi", "Bucharest, Romania", 4, 71 , "560")

)

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
    "0745498322"
)

val propertyDummy : PropertyShort = PropertyShort( 4.2, 69, "Apartament 2 camere Politehnica", "Bucharest, Romania", 2, 46 , "350")