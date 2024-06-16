package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.araducanu.rentconnect.components.MySearchBarComponent
import ro.araducanu.rentconnect.components.PropertiesList
import ro.araducanu.rentconnect.data.models.propertyList
import ro.araducanu.rentconnect.data.viewmodels.PropertyImageViewModel
import ro.araducanu.rentconnect.data.viewmodels.PropertyViewModel
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen

@Composable
fun SearchScreen(
    propertyViewModel: PropertyViewModel = viewModel(),
    propertyImageViewModel: PropertyImageViewModel = viewModel()

) {
    //val properties by propertyViewModel.propertiess.observeAsState(initial = emptyList())


    val propertiesFiltered by propertyViewModel.propertiesList.observeAsState(initial = emptyList())
    var searchQuery by remember {
        mutableStateOf("")
    }

    // Actualizează query-ul de căutare în ViewModel
    val onSearchChanged = { query: String ->
        searchQuery = query
        propertyViewModel.updateSearchQuery(query)
    }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            //.padding(28.dp)
            .background(Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            //TopCardComponent()
            //Spacer(modifier = Modifier.height(100.dp))
            MySearchBarComponent(onSearchChanged = onSearchChanged)
            PropertiesList(
                propertiesList = propertiesFiltered,
                inAddScreen = false
            )
        }
    }
}

@Composable
@Preview
fun SearchScreenPreview() {
    SearchScreen()
}