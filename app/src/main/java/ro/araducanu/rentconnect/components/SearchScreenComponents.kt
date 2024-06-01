package ro.araducanu.rentconnect.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.araducanu.rentconnect.ui.theme.BackgroundColor
import ro.araducanu.rentconnect.ui.theme.BlackText
import ro.araducanu.rentconnect.ui.theme.GrayColorIconWriting
import ro.araducanu.rentconnect.ui.theme.GrayIcons


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
fun MySearchBarComponent() {
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
            onValueChange = { textState = it },
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
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray, RoundedCornerShape(50))
                    .clip(RoundedCornerShape(50))
            ) {
                Text(
                    text = "FILTER",
                    color = BlackText, // Use the appropriate color value
                    fontSize = 14.sp,
                    modifier = Modifier.padding(5.dp) // Apply padding to the text instead
                )
            }

            Spacer(
                modifier = Modifier
                    .width(1.dp)
            )

            Button(
                onClick = { /* Do nothing as button is inactive */ },
                enabled = true,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray, RoundedCornerShape(50))
                    .clip(RoundedCornerShape(50))
            ) {
                Text(
                    text = "SORT",
                    color = BlackText, // Use the appropriate color value
                    fontSize = 14.sp,
                    modifier = Modifier.padding(5.dp) // Apply padding to the text instead
                )
            }
        }
    }
}



@Composable
@Preview
fun MyBottomNavigationBarPreview() {
    Scaffold(
        topBar = {
                 MySearchBarComponent()
        },
        bottomBar =
        {
            MyBottomNavigationBarComponent(selectedIcon = remember { mutableStateOf(null) })
        }
    )
    { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.White
        ) {}
    }
    //MyBottomNavigationBarComponent(selectedIcon = remember { mutableStateOf(null) })
}

//@Composable
//@Preview
//fun MySearchBarPreview() {
//    MySearchBarComponent()
//}
