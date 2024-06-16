package ro.araducanu.rentconnect.data.add

sealed class AddUIEvent{

    data class TitleChanged(val title:String): AddUIEvent()
    data class DescriptionChanged(val description: String) : AddUIEvent()
    data class AddressChanged(val address:String): AddUIEvent()

    data class TypeChanged(val type:String): AddUIEvent()
    data class FloorChanged(val floor:String): AddUIEvent()
    data class YearOfConstructionChanged(val yearOfConstruction:Int): AddUIEvent()
    data class RoomsChanged(val rooms:Int): AddUIEvent()
    data class PriceChanged(val price:String): AddUIEvent()
    data class SizeChanged(val size : Int): AddUIEvent()




    object SubmitButtonClicked : AddUIEvent()
}
