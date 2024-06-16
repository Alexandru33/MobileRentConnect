package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import ro.araducanu.rentconnect.components.AvatarComponent
import ro.araducanu.rentconnect.components.HeadingTextComponent
import ro.araducanu.rentconnect.components.ProfileDetailsRow
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen

@Composable
@Preview
fun ProfileScreen() {

    Column() {


        AvatarComponent()
        Spacer( modifier = Modifier.height(20.dp))
        HeadingTextComponent(value = "Alexandru Raducanu")
        Spacer ( modifier = Modifier.height(100.dp))


        ProfileDetailsRow(icon = Icons.Default.Logout, key = "Log Out",
            onClickFunction = {
                RentConnectAppRouter.makeScreenStackEmpty()
                RentConnectAppRouter.navigateTo(Screen.StartScreen)
                FirebaseAuth.getInstance().signOut()
            })
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