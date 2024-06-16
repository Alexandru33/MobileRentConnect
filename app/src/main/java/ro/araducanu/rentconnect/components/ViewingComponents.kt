package ro.araducanu.rentconnect.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.araducanu.rentconnect.data.models.Viewing
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewingCard(
    viewing : Viewing,
    onCLick : () -> Unit
){
    val date = (viewing.date.toDate()).toString().substring(0, 10)
    val time = viewing.time

    var availableString : String

    if ( viewing.status.equals("free"))
    {
        availableString = "Available for a view:  $date"
    }
    else
    {
        availableString = "Slot booked by : ${viewing.emailTenant} for $date"
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 2.dp)
            .background(Color.LightGray)
            .border(1.dp , Color.Black),
        onClick = onCLick

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = availableString ,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                color = Color.Black
            )
            Text(
                text = time,
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                color = Color.Black
            )
        }
    }


}
@Composable
@Preview
fun ViewingPreview() {
}