package ro.araducanu.rentconnect.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.araducanu.rentconnect.components.PropertyDetailComponent
import ro.araducanu.rentconnect.data.viewmodels.PropertyViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PropertyDetailScreen(
    propertyViewModel : PropertyViewModel = viewModel()
) {
    val selectedProperty = propertyViewModel.selectedProperty

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            //.padding(28.dp)
            .background(Color.White)
    ){
        PropertyDetailComponent(
            propertyLong = selectedProperty.value
            )
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PropertyDetailScreenPreview() {
    PropertyDetailScreen()
}