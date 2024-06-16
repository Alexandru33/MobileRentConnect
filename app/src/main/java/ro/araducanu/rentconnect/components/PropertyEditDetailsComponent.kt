package ro.araducanu.rentconnect.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Airlines
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Elevator
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.filled.TypeSpecimen
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.araducanu.rentconnect.data.models.PropertyLong
import ro.araducanu.rentconnect.data.models.propertyLongDummy
import ro.araducanu.rentconnect.data.viewmodels.PropertyImageViewModel
import ro.araducanu.rentconnect.data.viewmodels.PropertyViewModel
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.ui.theme.BlackText
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue
import ro.araducanu.rentconnect.ui.theme.White

@Composable
fun PropertyEditDetailComponent(
    propertyLong: PropertyLong?
)
{

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ){
        ImageCarousel(PropertyImageViewModel(), ""/*propertyLong?.id!!*/)
        PropertyEditLongTextCard()
        Spacer(modifier = Modifier.weight(1f))
        //BottomButtonsDetailsPropertyComponent(propertyLong = propertyLong)
    }
}


@Composable
@Preview
fun PropertyEditDetailComponentPreview(){
    //PropertyEditDetailComponent(propertyLong = propertyLongDummy)
    //PropertyEditLongTextCard(propertyLong = propertyLongDummy)
}


@Composable
fun PropertyEditLongTextCard(
    //propertyLong: PropertyLong,
    propertyViewModel: PropertyViewModel = viewModel()
) {
    val propertyLong = propertyViewModel.selectedProperty.value!!


//    var title by remember { mutableStateOf(propertyViewModel.selectedProperty.value?.title ?: "") }
//    var description by remember { mutableStateOf(propertyViewModel.selectedProperty.value?.description ?: "") }
//    var price by remember { mutableStateOf(propertyViewModel.selectedProperty.value?.price ?: "") }
//    var rooms by remember { mutableStateOf(propertyViewModel.selectedProperty.value?.rooms ?: 0) }

    var title by remember { mutableStateOf(propertyLong.title ?: "") }
    var description by remember { mutableStateOf(propertyLong.description ?: "") }
    var price by remember { mutableStateOf(propertyLong.price ?: "") }
    var rooms by remember { mutableStateOf(propertyLong.rooms ?: 0) }


    LaunchedEffect(title) {
        propertyViewModel.updateTitle(title)
    }

    LaunchedEffect(description) {
        propertyViewModel.updateDescription(description)
    }

    LaunchedEffect(price) {
        propertyViewModel.updatePrice(price)
    }


    LaunchedEffect(rooms) {
        propertyViewModel.updateRooms(rooms)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .verticalScroll(rememberScrollState())


    ) {

        TextField(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Title,
                    contentDescription = "Edit",
                    modifier = Modifier.padding(start = 5.dp)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedLabelColor = Color(0xB92DC1C5),
                focusedLeadingIconColor = Color(0xB92DC1C5)
            ),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") })

        TextField(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Image,
                    contentDescription = "Edit",
                    modifier = Modifier.padding(start = 5.dp)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedLabelColor = Color(0xB92DC1C5),
                focusedLeadingIconColor = Color(0xB92DC1C5)
            ),
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") })

        TextField(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.MonetizationOn,
                    contentDescription = "Edit",
                    modifier = Modifier.padding(start = 5.dp)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedLabelColor = Color(0xB92DC1C5),
                focusedLeadingIconColor = Color(0xB92DC1C5)
            ),
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") })

        TextField(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Title,
                    contentDescription = "Edit",
                    modifier = Modifier.padding(start = 5.dp)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedLabelColor = Color(0xB92DC1C5),
                focusedLeadingIconColor = Color(0xB92DC1C5)
            ),
            value = rooms.toString(),
            onValueChange = {
                if ( it.isEmpty()) {
                            rooms = 0
                        }
                else{
                    rooms = Integer.parseInt(it)
                        }
                            },
            label = { Text("Number of Rooms") })

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)

        ) {
            Button(
                onClick =
                {
                    RentConnectAppRouter.navigateBack()
                },
                enabled = true,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(PrimaryBlue),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .wrapContentHeight()
                    .weight(1f)
                    .border(1.dp, Color.Gray, RoundedCornerShape(50))
                    .height(40.dp)
                    .clip(RoundedCornerShape(50))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        Icons.Default.Cancel,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Cancel",
                        color = White, // Use the appropriate color value
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                    )
                }

            }
            Button(
                onClick = {
                    propertyViewModel.updateProperty()
                    RentConnectAppRouter.navigateTo(Screen.AddScreen)

                },
                enabled = true,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(White),
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
                        Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Update",
                        color = BlackText, // Use the appropriate color value
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                    )
                }
            }
        }



//        Row (
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxWidth()
//        ){
//            Text(
//                text = propertyLong.title,
//                style = MaterialTheme.typography.h5,
//                fontSize = 16.sp,
//                modifier = Modifier.padding(bottom = 4.dp)
//            )
//        }
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp, bottom = 16.dp),
//            shape = RoundedCornerShape(10),
//            elevation = CardDefaults.cardElevation(
//                defaultElevation = 2.dp,
//                pressedElevation = 6.dp
//            ),
//            colors = CardColors(
//                containerColor = Color.Transparent,
//                contentColor = Color.Black,
//                disabledContentColor = Color.Black,
//                disabledContainerColor = Color.Black
//            )
//        ) {
//            Text(
//                text = propertyLong.description,
//                style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight(400)),
//                textAlign = TextAlign.Justify,
//                modifier = Modifier.padding(10.dp)
//            )
//        }
//        DetailRow(icon = Icons.Default.TypeSpecimen, key = "Type:", value = propertyLong.type)
//        DetailRow(icon = Icons.Default.Elevator, key = "Floor:", value = propertyLong.floor)
//        DetailRow(icon = Icons.Default.Construction, key = "Year of Construction:", value = propertyLong.yearOfConstruction.toString())
//        DetailRow(icon = Icons.Default.MeetingRoom, key = "No. of Rooms:", value = propertyLong.rooms.toString())
//        DetailRow(icon = Icons.Default.Map, key = "Location:", value = propertyLong.location)
//        DetailRow(icon = Icons.Default.MonetizationOn, key = "Price:", value = propertyLong.price)


    }


}

