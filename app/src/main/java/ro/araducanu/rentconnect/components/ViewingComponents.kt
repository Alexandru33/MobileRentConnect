package ro.araducanu.rentconnect.components

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import ro.araducanu.rentconnect.data.models.Viewing
import ro.araducanu.rentconnect.ui.theme.BlackText
import ro.araducanu.rentconnect.ui.theme.GrayIcons
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue
import ro.araducanu.rentconnect.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewingCard(
    viewing : Viewing,
    onCLick : () -> Unit
){
    val date = (viewing.date.toDate()).toString().substring(0, 10)


    var generateContractButton : Boolean =
            viewing.status.equals("booked") && (viewing.emailLandlord.equals(Firebase.auth.currentUser?.email)
                    && (viewing.date < Timestamp.now())  )

    var deleteViewingButton : Boolean =
        generateContractButton
    var availableString : String = ""
    var backgroundColor : Color = Color.Red
    if ( viewing.status.equals("free"))
    {
        availableString = "Available for a view:  $date"
        backgroundColor = White
    }
    else if ( viewing.status.equals("booked"))
    {
        availableString = "Slot booked by : ${viewing.emailTenant} for $date"
        backgroundColor = Color.LightGray
    }


    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(30.dp, 120.dp)
            .padding(horizontal = 2.dp)
            .background(backgroundColor)
            .border(1.dp , Color.Black),
        backgroundColor = backgroundColor,
        onClick = onCLick

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = availableString ,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                text = viewing.time,
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                color = Color.Black
            )
            if ( deleteViewingButton )
                Button(
                    onClick = {},
                    enabled = true,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors( White),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
                        .wrapContentHeight()
                        .weight(1f)
                        .border(1.dp, Color.Red, RoundedCornerShape(50))
                        .height(40.dp)
                        .clip(RoundedCornerShape(50))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {

                        Icon(
                            Icons.Default.Delete,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.Black
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Delete Viewing",
                            color = BlackText, // Use the appropriate color value
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                        )
                    }
                }
            if ( generateContractButton)
                Button(
                onClick = {},
                enabled = true,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors( White),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .wrapContentHeight()
                    .weight(1f)
                    .border(1.dp, PrimaryBlue, RoundedCornerShape(50))
                    .height(40.dp)
                    .clip(RoundedCornerShape(50))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        Icons.Default.AttachFile,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Generate Contract",
                        color = BlackText, // Use the appropriate color value
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                    )
                }

            }
        }

    }


}
@Composable
@Preview
fun ViewingPreview() {
}