package ro.araducanu.rentconnect.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Elevator
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Square
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.filled.TypeSpecimen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.data.add.AddUIEvent
import ro.araducanu.rentconnect.data.add.AddViewModel
import ro.araducanu.rentconnect.data.user.UserViewModel
import ro.araducanu.rentconnect.data.viewmodels.PropertyImageViewModel
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.screens.AddNewDetailsScreen
import ro.araducanu.rentconnect.ui.theme.BlackText
import ro.araducanu.rentconnect.ui.theme.GrayIcons
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue
import ro.araducanu.rentconnect.ui.theme.White
import java.lang.Integer.parseInt


@Composable
fun AddPropertyComponent(addViewModel: AddViewModel = viewModel()) {
Column(
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.Transparent)
        //.verticalScroll(rememberScrollState())
) {

    AddTextComponent(labelValue = "Insert Title...", painterResource = Icons.Default.Title,
        onTextSelected = {
                         addViewModel.onEvent(AddUIEvent.TitleChanged(it))
    } , errorStatus = true)
    AddDescriptionComponent(
        labelValue = "Add a Description...",
        painterResource = Icons.Default.Description,
        onTextSelected = {
            addViewModel.onEvent(AddUIEvent.DescriptionChanged(it))
        },
        errorStatus = true
    )
    AddTextComponent(labelValue = "Address...", painterResource = Icons.Default.Map,
        onTextSelected = {
            addViewModel.onEvent(AddUIEvent.AddressChanged(it))
        }, errorStatus = true )
    AddTextComponent(labelValue = "Insert Type...", painterResource = Icons.Default.TypeSpecimen,
        onTextSelected = {
            addViewModel.onEvent(AddUIEvent.TypeChanged(it))
        }, errorStatus = true )
    AddNumberComponent(labelValue = "Floor no.", painterResource = Icons.Default.Elevator,
        onTextSelected = {
            addViewModel.onEvent(AddUIEvent.FloorChanged(it))
        }, errorStatus = true )
    AddNumberComponent(labelValue = "Year of Construction...", painterResource = Icons.Default.Construction,
        onTextSelected = {
            addViewModel.onEvent(AddUIEvent.YearOfConstructionChanged(Integer.parseInt(it)))
        }, errorStatus = true)
    AddNumberComponent(labelValue = "No. of Rooms...", painterResource = Icons.Default.MeetingRoom,
        onTextSelected = {
            addViewModel.onEvent(AddUIEvent.RoomsChanged(Integer.parseInt(it)) )
        }, errorStatus = true)
    AddNumberComponent(labelValue = "Monthly Rent (in euros)", painterResource = Icons.Default.MonetizationOn,
        onTextSelected = {
            addViewModel.onEvent(AddUIEvent.PriceChanged(it))
        }, errorStatus = true)
    AddNumberComponent(labelValue = "Size (in square meters)", painterResource = Icons.Default.Square,
        onTextSelected = {
            addViewModel.onEvent(AddUIEvent.SizeChanged(Integer.parseInt(it)))
        }, errorStatus = true)
}

}

@Composable
fun AddNumberComponent(labelValue: String, painterResource: ImageVector, onTextSelected: (String) -> Unit, errorStatus: Boolean) {
    val textValue = remember {
        mutableStateOf("")
    }
    TextField(
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        singleLine = true,
        maxLines = 1,
        label = {Text(text = labelValue)},
        leadingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource,
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(4.dp))
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .width(1.dp)
                        .height(100.dp)
                )
            }
        },
        isError = !errorStatus,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Transparent, RoundedCornerShape(10))
    )

}

@Composable
fun AddTextComponent(
    labelValue: String,
    painterResource: ImageVector,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean
){
    val textValue = remember {
        mutableStateOf("")
    }
        TextField(
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                onTextSelected(it)
            },
            singleLine = true,
            maxLines = 1,
            label = {Text(text = labelValue)},
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource,
                            contentDescription = "",
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Divider(
                            color = Color.Black,
                            modifier = Modifier
                                .width(1.dp)
                                .height(100.dp)
                        )
                    }
                },
            isError = !errorStatus,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent, RoundedCornerShape(10))
        )

    }

@Composable
fun AddDescriptionComponent(
    labelValue: String,
    painterResource: ImageVector,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean
){
    val textValue = remember {
        mutableStateOf("")
    }
    TextField(
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        minLines = 5,
        label = {Text(text = labelValue)},
        leadingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource,
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(4.dp))
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .width(1.dp)
                        .height(120.dp)
                )
            }
        },
        isError = !errorStatus,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .background(Color.Transparent, RoundedCornerShape(10))
    )

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarouselPlane(imageList: List<Uri>, propertyImageViewModel: PropertyImageViewModel = viewModel()) {

    val imageList  = propertyImageViewModel.imageList
    val pagerState = rememberPagerState(initialPage = 0)

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(shape = RoundedCornerShape(0, 0, 10, 10))
        ) {
            HorizontalPager(
                count = if (imageList.isEmpty()) 1 else imageList.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                if (imageList.isNotEmpty()) {
                    Image(
                        painter = rememberImagePainter(imageList[page]),
                        contentDescription = "Plane Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    // Display default image when there are no images in the list
                    Image(
                        painter = painterResource(R.drawable.apartament_image),
                        contentDescription = "no image found",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                activeColor = Color.White,
                inactiveColor = Color.Gray
            )

            ImagePickerButton(
                propertyImageViewModel = propertyImageViewModel,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ImagePickerButton(propertyImageViewModel: PropertyImageViewModel = viewModel() , modifier : Modifier) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { propertyImageViewModel.addImage(it) }
        }
    IconButton(
        modifier = Modifier
            .padding(top = 170.dp, start = 340.dp, end = 10.dp),
        colors = IconButtonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF3C4B65),
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.LightGray
        ),
        onClick = {
            launcher.launch("image/*")
        }) {
        androidx.compose.material3.Icon(Icons.Default.Add, contentDescription = "Add Image")
    }
}




@Composable
fun BottomButtonsAddPropertyComponent(

    addViewModel: AddViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    propertyImageViewModel: PropertyImageViewModel = viewModel()
)
{
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)

    ){
        Button(
            onClick = { /* Do nothing as button is inactive */ },
            enabled = true,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors( Color.Red),
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
                addViewModel.addUIState.value.phone = userViewModel.userUIState.value.phone
                addViewModel.addUIState.value.email = userViewModel.userUIState.value.email

                CoroutineScope(Dispatchers.IO).launch {

                    addViewModel.onEvent(AddUIEvent.SubmitButtonClicked)
                    while ( addViewModel.addUIState.value.id == "")
                    {
                        //Log.d("Alex" , "Idul este ${addViewModel.addUIState.value.id}")
                    }
                    propertyImageViewModel.uploadImagesForNewModel(addViewModel.addUIState.value.id)
                }
                      },
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
                    Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Submit",
                    color = BlackText, // Use the appropriate color value
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                )
            }
        }
    }
}

@Composable
fun AddNewPropertyButton() {
    Button(
        onClick = {
                  RentConnectAppRouter.navigateTo(Screen.AddNewDetailsScreen)
        },
        enabled = true,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors( White),
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
            .wrapContentHeight()
            .border(1.dp, PrimaryBlue, RoundedCornerShape(50))
            .height(40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Add a new Property",
                color = BlackText, // Use the appropriate color value
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
            )
        }
    }
}
