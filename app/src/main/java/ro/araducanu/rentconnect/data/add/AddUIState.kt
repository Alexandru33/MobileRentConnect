package ro.araducanu.rentconnect.data.add

data class AddUIState(
    var title  :String = "",
    var description  :String = "",
    var location  :String = "",
    var type  :String = "",
    var floor  :String = "",
    var year  : Int = 0,
    var rooms  :Int = 0,
    var price  :String = "",
    var phone : String = "",
    var email : String = "",
    var id : String  = "",
    var size : Int = 0,



    var titleError :Boolean = false,
    var descriptionError : Boolean = false,
    var addressError :Boolean = false,
    var typeError :Boolean = false,
    var floorError :Boolean = false,
    var yearError :Boolean = false,
    var roomsError :Boolean = false,
    var priceError :Boolean = false,
    var sizeError : Boolean = false


)
