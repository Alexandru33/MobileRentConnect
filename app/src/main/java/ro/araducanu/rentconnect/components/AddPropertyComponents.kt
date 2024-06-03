package ro.araducanu.rentconnect.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
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
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.data.models.PropertyLong
import ro.araducanu.rentconnect.ui.theme.BlackText
import ro.araducanu.rentconnect.ui.theme.GrayIcons
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue
import ro.araducanu.rentconnect.ui.theme.White


@Composable
fun AddPropertyComponent() {
Column(
    modifier = Modifier.fillMaxWidth()
) {

    AddTextComponent(labelValue = "Insert Title...", painterResource = Icons.Default.Title, onTextSelected = {} , errorStatus = true)
    AddDescriptionComponent(
        labelValue = "Add a Description...",
        painterResource = Icons.Default.Description,
        onTextSelected = {},
        errorStatus = true
    )
    AddTextComponent(labelValue = "Address...", painterResource = Icons.Default.Map, onTextSelected = {}, errorStatus = true )
    AddTextComponent(labelValue = "Insert Type...", painterResource = Icons.Default.TypeSpecimen, onTextSelected = {}, errorStatus = true )
    AddNumberComponent(labelValue = "Floor no.", painterResource = Icons.Default.Elevator, onTextSelected = {}, errorStatus = true )
    AddNumberComponent(labelValue = "Year of Construction...", painterResource = Icons.Default.Construction, onTextSelected = {}, errorStatus = true)
    AddNumberComponent(labelValue = "No. of Rooms...", painterResource = Icons.Default.MeetingRoom, onTextSelected = {}, errorStatus = true)
    AddNumberComponent(labelValue = "Monthly Rent (in euros)", painterResource = Icons.Default.MonetizationOn, onTextSelected = {}, errorStatus = true)
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
            .height(40.dp)
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
                .height(40.dp)
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
    )

}
@Composable
fun DetailRowInput(
    icon : ImageVector,
    key : String,
    value : String = "",
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, GrayIcons, RoundedCornerShape(10))
            .height(30.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ){
        Icon(
            icon,
            contentDescription = null,
            modifier  = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
                .size(20.dp)
        )
        Text(
            text = key
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            modifier = Modifier.padding(end = 8.dp)
        )

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarouselPlane(imageList: List<Uri>) {
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
        }
    }
}

@Composable
fun ImagePickerButton(updateImageList: (Uri) -> Unit) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.also { uri ->
                    updateImageList(uri)
                }
            }
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
            launcher.launch(Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
                addCategory(Intent.CATEGORY_OPENABLE)
            })
        }) {
        androidx.compose.material3.Icon(Icons.Default.Add, contentDescription = "Add Image")
    }
}




@Composable
fun BottomButtonsAddPropertyComponent(
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
            onClick = { /* Do nothing as button is inactive */ },
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
@Preview
fun AddPropertyComponentPreview() {
    //AddPropertyComponent()
    var images = remember { mutableStateListOf<Uri>() }

    Scaffold(
        // Define your bottom bar here if needed
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), color = Color.White
        ) {
            Column {
                Box {
                    ImageCarouselPlane(images)

                    ImagePickerButton { newImageUri ->
                        images.add(newImageUri)
                    }
                }
                AddPropertyComponent()
                Spacer( modifier = Modifier.weight(1f) )
                BottomButtonsAddPropertyComponent()


            }
        }
    }
}