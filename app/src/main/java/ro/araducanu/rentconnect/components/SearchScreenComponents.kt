package ro.araducanu.rentconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.data.models.PropertyLong
import ro.araducanu.rentconnect.data.models.propertyLongDummy
import ro.araducanu.rentconnect.data.viewmodels.PropertyImageViewModel
import ro.araducanu.rentconnect.data.viewmodels.PropertyViewModel
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.ui.theme.GrayColorIconWriting
import ro.araducanu.rentconnect.ui.theme.GrayIcons
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue
import ro.araducanu.rentconnect.ui.theme.White


@Composable
fun MyBottomNavigationBarComponent(selectedIcon : MutableState<ImageVector?>) {
    BottomAppBar(
        modifier = Modifier.
            border(2.dp, GrayColorIconWriting, RoundedCornerShape(10,10,0,0)),
        backgroundColor = Color.Transparent, // Use a simple color for debugging
        contentColor = Color.White,
        elevation = 0.dp )// Remove any shadow
    {
        
        IconButton(
            onClick = {
                selectedIcon.value = Icons.Default.Search
                if (!RentConnectAppRouter.currentScreen.equals(Screen.SearchScreen))
                    RentConnectAppRouter.navigateTo(Screen.SearchScreen)
            },
            modifier = Modifier
                .weight(1f)
                .height(100.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    tint = if (selectedIcon.value == Icons.Default.Search) Color.Black else GrayColorIconWriting
                )
                Text(
                    text = "Search",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = GrayColorIconWriting
                    )
                )
            }
            
        }
        IconButton(
            onClick = {
                selectedIcon.value = Icons.Default.CalendarMonth
                if (!RentConnectAppRouter.currentScreen.equals(Screen.ViewingsScreen))
                    RentConnectAppRouter.navigateTo(Screen.ViewingsScreen)
            },
            modifier = Modifier
                .weight(1f)
                .height(100.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    tint = if (selectedIcon.value == Icons.Default.CalendarMonth) Color.Black else GrayColorIconWriting
                )
                Text(
                    text = "Viewings",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = GrayColorIconWriting
                    )
                )
            }

        }
        IconButton(
            onClick = {
                selectedIcon.value = Icons.Default.Add
                if (!RentConnectAppRouter.currentScreen.equals(Screen.AddScreen))
                    RentConnectAppRouter.navigateTo(Screen.AddScreen)
            },
            modifier = Modifier
                .weight(1f)
                .height(100.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    tint = if (selectedIcon.value == Icons.Default.Add) Color.Black else GrayColorIconWriting
                )
                Text(
                    text = "Add",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = GrayColorIconWriting,
                        
                    )
                )
            }

        }
        IconButton(
            onClick = {
                selectedIcon.value = Icons.Default.Article
                if (!RentConnectAppRouter.currentScreen.equals(Screen.ContractsScreen))
                RentConnectAppRouter.navigateTo(Screen.ContractsScreen)

            },
            modifier = Modifier
                .weight(1f)
                .height(100.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    Icons.Default.Article,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    tint = if (selectedIcon.value == Icons.Default.Article) Color.Black else GrayColorIconWriting
                )
                Text(
                    text = "Contracts",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = GrayColorIconWriting
                    )
                )
            }

        }
        IconButton(
            onClick = {
                selectedIcon.value = Icons.Default.Person
                if (!RentConnectAppRouter.currentScreen.equals(Screen.ProfileScreen))
                    RentConnectAppRouter.navigateTo(Screen.ProfileScreen)
            },
            modifier = Modifier
                .weight(1f)
                .height(100.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    tint = if (selectedIcon.value == Icons.Default.Person) Color.Black else GrayColorIconWriting
                )
                Text(
                    text = "Profile",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = GrayColorIconWriting
                    )
                )
            }

        }
    }

}

@Composable
fun MySearchBarComponent(onSearchChanged : (String) -> Unit) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(16.dp)
    ) {
        // Search bar
        TextField(
            value = textState,
            onValueChange = { it ->
                textState = it
                onSearchChanged(textState.text)
                            },
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(50))
                .border(1.dp, Color.Gray, RoundedCornerShape(50))
                .height(50.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Filter and Sort buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent, RoundedCornerShape(50))
        ) {
            Button(
                onClick = { /* Do nothing as button is inactive */ },
                enabled = true,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue),
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f)
                    .border(1.dp, Color.Gray, RoundedCornerShape(50))
                    .clip(RoundedCornerShape(50))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){

                    Icon(
                        Icons.Default.FilterAlt,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )

                    Spacer(modifier =  Modifier.width(10.dp))

                    Text(
                        text = "Filter",
                        color = White, // Use the appropriate color value
                        fontSize = 14.sp,
                        modifier = Modifier.padding(5.dp) // Apply padding to the text instead
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .width(1.dp)
            )

            Button(
                onClick = { /* Do nothing as button is inactive */ },
                enabled = true,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f)
                    .border(1.dp, Color.Gray, RoundedCornerShape(50))
                    .clip(RoundedCornerShape(50))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){

                    Icon(
                        Icons.Default.Sort,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier =  Modifier.width(10.dp))

                    Text(
                        text = "Sort",
                        color = Color.Black, // Use the appropriate color value
                        fontSize = 14.sp,
                        modifier = Modifier.padding(5.dp) // Apply padding to the text instead
                    )
                }
            }
        }
    }
}



@Composable
@Preview
fun MySearchBarPreview() {
   PropertyCard(propertyLong = propertyLongDummy, inAddScreen = false)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PropertyCard(
    propertyLong : PropertyLong,
    propertyImageViewModel : PropertyImageViewModel = viewModel(),
    propertyViewModel : PropertyViewModel = viewModel(),
    inAddScreen: Boolean
) {

    val mainImages by propertyImageViewModel.mainImages.observeAsState(initial = emptyMap())
    val mainImageUrl = mainImages[propertyLong.id]


    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(160.dp),
        onClick = {
            propertyViewModel.selectProperty(propertyLong)
            if (!inAddScreen)
                RentConnectAppRouter.navigateTo(Screen.PropertyDetailScreen)
            else
                RentConnectAppRouter.navigateTo(Screen.PropertyEditScreen)
                  },
        enabled = true
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp)
                    .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = rememberImagePainter(mainImageUrl ?: ""),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
            }
            Spacer( modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD700), // Gold color for star
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${propertyLong.rating} (${propertyLong.reviews} reviews)",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                    Text(
                        text = propertyLong.title,
                        style = MaterialTheme.typography.h6,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = propertyLong.location,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Icon(
                        Icons.Default.Bed,
                        contentDescription = null,
                        tint = GrayIcons,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${propertyLong.rooms} room",
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Default.CropSquare,
                        contentDescription = null,
                        tint = GrayIcons, // Gold color for star
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${propertyLong.size} m²",
                        style = MaterialTheme.typography.body2
                    )
                }
                Text(
                    text = "€${propertyLong.price} / month",
                    style = MaterialTheme.typography.h6,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}


@Composable
fun TopCardComponent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        backgroundColor = PrimaryBlue,
        shape = RoundedCornerShape(bottomEndPercent = 10 , bottomStartPercent = 10)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_color_no_background),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
            )
        }

    }

}


@Composable
fun PropertiesList(propertiesList: List<PropertyLong>, inAddScreen : Boolean)  {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(propertiesList, key = { property -> property.title}) { propertyLong ->
            PropertyCard(propertyLong, inAddScreen = inAddScreen)
        }
    }

}
