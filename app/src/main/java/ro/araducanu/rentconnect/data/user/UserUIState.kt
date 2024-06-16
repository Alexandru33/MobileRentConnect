package ro.araducanu.rentconnect.data.user

data class UserUIState(
    var firstName  :String = "",
    var lastName : String = "",
    var email  :String = "",
    var phone  :String = "",




    var nameError :Boolean = false,
    var emailError : Boolean = false,
    var phoneError :Boolean = false,



)
