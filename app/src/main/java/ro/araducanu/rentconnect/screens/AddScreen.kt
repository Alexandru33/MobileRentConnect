package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.araducanu.rentconnect.components.AddNewPropertyButton
import ro.araducanu.rentconnect.components.MySearchBarComponent
import ro.araducanu.rentconnect.components.PropertiesList
import ro.araducanu.rentconnect.data.models.propertyList
import ro.araducanu.rentconnect.data.viewmodels.PropertyViewModel
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen

@Composable
@Preview
fun AddScreen(
    propertyViewModel: PropertyViewModel = viewModel()

) {
    //propertyViewModel.fetchPropertiesOfUser()
    val propertiesOfUser by propertyViewModel.propertiesOfUser2.observeAsState(initial = emptyList())

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            //.padding(28.dp)
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.verticalScroll(rememberScrollState())
        ){
            AddNewPropertyButton()
            PropertiesList(
                propertiesList = propertiesOfUser,
                inAddScreen = true
            )
        }
    }
}