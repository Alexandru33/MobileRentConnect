package ro.araducanu.rentconnect.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.araducanu.rentconnect.components.AddPropertyComponent
import ro.araducanu.rentconnect.components.BottomButtonsAddPropertyComponent
import ro.araducanu.rentconnect.components.ImageCarouselPlane
import ro.araducanu.rentconnect.components.ImagePickerButton
import ro.araducanu.rentconnect.data.add.AddViewModel
import ro.araducanu.rentconnect.data.user.UserViewModel
import ro.araducanu.rentconnect.data.viewmodels.PropertyImageViewModel
import ro.araducanu.rentconnect.navigation.Screen

@Composable
@Preview
fun AddNewDetailsScreen( addViewModel: AddViewModel = viewModel(), userViewModel: UserViewModel  = viewModel(), propertyImageViewModel: PropertyImageViewModel = viewModel())
{
    var images = remember { mutableStateListOf<Uri>() }

    Scaffold(
        // Define your bottom bar here if needed
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.White
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ){
                Box {
                    ImageCarouselPlane(images)
                }
                AddPropertyComponent(addViewModel)
                Spacer( modifier = Modifier.weight(1f) )
                BottomButtonsAddPropertyComponent(addViewModel, userViewModel, propertyImageViewModel)


            }
        }
    }

}