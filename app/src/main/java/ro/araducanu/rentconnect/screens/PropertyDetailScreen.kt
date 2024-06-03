package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ro.araducanu.rentconnect.components.PropertyDetailComponent
import ro.araducanu.rentconnect.data.models.propertyLongDummy

@Composable
fun PropertyDetailScreen() {

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
            PropertyDetailComponent(propertyLong = propertyLongDummy)
        }
    }

}

@Composable
@Preview
fun PropertyDetailScreenPreview() {
    PropertyDetailScreen()
}