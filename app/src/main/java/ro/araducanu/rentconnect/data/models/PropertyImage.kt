package ro.araducanu.rentconnect.data.models

import android.net.Uri

data class PropertyImage(
    var mainImage : Uri,
    var imageList : List<Uri>

) {

    constructor() : this(Uri.EMPTY, emptyList())

}