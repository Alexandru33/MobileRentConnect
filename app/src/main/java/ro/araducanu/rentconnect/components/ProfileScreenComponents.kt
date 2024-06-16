package ro.araducanu.rentconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.ui.theme.GrayIcons

@Composable
fun ProfileDetailsRow(
    icon : ImageVector,
    key : String,
    onClickFunction : () -> Unit

){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, GrayIcons, RoundedCornerShape(10))
            .height(30.dp)
            .clickable {
                onClickFunction()
            },
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


    }
}

@Composable
fun AvatarComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_picture), // Replace with your image resource
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray, CircleShape)
                .fillMaxWidth(0.4f)
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun ProfilePreview() {

    Column() {


        AvatarComponent()
        Spacer( modifier = Modifier.height(20.dp))
        HeadingTextComponent(value = "Alexandru Raducanu")
        Spacer ( modifier = Modifier.height(100.dp))


        ProfileDetailsRow(icon = Icons.Default.Logout, key = "Log Out",
            onClickFunction = {})
        ProfileDetailsRow(icon = Icons.Default.Edit, key = "Edit Profile",
            onClickFunction = {})
        ProfileDetailsRow(icon = Icons.Default.History, key = "History",
            onClickFunction = {})
        ProfileDetailsRow(icon = Icons.Default.House, key = "My Properties",
            onClickFunction = {
                RentConnectAppRouter.navigateTo(Screen.AddScreen)
            })
        ProfileDetailsRow(icon = Icons.Default.Delete, key = "Delete Account",
            onClickFunction = {})
    }

}