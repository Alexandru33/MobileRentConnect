package ro.araducanu.rentconnect.data.user

sealed class UserUIEvent{

    data class FirstNameChanged(val firstName:String): UserUIEvent()
    data class LastNameChanged(val lastName:String): UserUIEvent()
    data class EmailChanged(val email: String) : UserUIEvent()
    data class PhoneChanged(val phone:String): UserUIEvent()






    object CreateButtonClicked : UserUIEvent()
    object FetchUserButtonClicked : UserUIEvent()
    object ModifyUserButtonClicked : UserUIEvent()
}
